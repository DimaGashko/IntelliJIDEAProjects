package com.labs.lab5.ELib.models.storage;

import java.io.*;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.function.Predicate;

/**
 * Класс для хранения данных на основании текстового файла
 * @param <T> класс хранимых данных
 * @version 0.0.0.1
 *
 * TODO: заменить Array на ArrayList
 */
public class TextStorage<T> implements IStorage<T> {
    static final private int DEF_BUFFER_SIZE = 100;

    //Текстовый файл для хранения данных
    private File dataFile;

    // Класс хранимих данных
    // Используется для создания new T[]
    // TODO: убрать после внедрения ArrayList
    private Class dataClass;

    // Массив хранимых данных
    private T[] data;

    // Количество данных
    private int len = 0;

    /**
     * TODO: после внедрения ArrayList убрать аргумент dataClass
     */
    public TextStorage(String url, Class dataClass) {
        this.dataClass = dataClass;

        this.data = getTArray(DEF_BUFFER_SIZE);
        initFile(url);

        load();
    }

    /**
     * TODO: после внедрения ArrayList убрать аргумент dataClass
     */
    public TextStorage(String url, int bufferSize, Class dataClass) {
        this.dataClass = dataClass;

        this.data = getTArray(bufferSize);
        initFile(url);

        load();
    }

    @Override
    public boolean add(T item) {
        addToArr(item);
        save(item);
    }

    @Override
    public T[] getArrOfData() {
        return Arrays.copyOf(data, len);
    }

    @Override
    public T[] getArrOfData(Predicate<T> filter) {
        return Arrays.stream(getArrOfData()).filter(filter).toArray(this::getTArray);
    }

    @Override
    public boolean remove(T item) {
        return false;
    }

    private void addToArr(T item) {
        if (len == data.length) return;
        data[len++] = item;
    }

    /**
     * Загружает данные из текстового файла в массив данных
     * @return true если загрузка удалась
     */
    private boolean load() {
        String itemStr;

        try {
            var reader = new BufferedReader(new FileReader(dataFile));

            while ((itemStr = reader.readLine()) != null) {
                addToArr(parse(itemStr));
            }

            reader.close();
        } catch (IOException err) {

        }

        return true;
    }

    /**
     * Сохраняет переданный элемент в текстовом файле
     * @param item сохраняемый элемент
     * @param append true - записывать в конец файла
     * TODO: Exception
     */
    private void save(T item, boolean append) {
        try {
            var writer = new PrintWriter(new FileWriter(dataFile, true));
            writer.println(item.toString());
            writer.close();
        } catch (IOException err) {

        }
    }

    /**
     * Записывает в файл строку
     * @param str строка для записи
     * @param append запись в конец файла
     * TODO: Exception
     */
    private void writeStrToFile(String str, boolean append) {
        try {
            var writer = new PrintWriter(new FileWriter(dataFile, true));
            writer.println(str);
            writer.close();
        } catch (IOException err) {

        }
    }

    /**
     * Записывает в файл строки
     * @param strs массив строк для записи
     * @param append запись в конец файла
     * TODO: Exception
     */
    private void writeArrStrToFile(String[] strs, boolean append) {
        try {
            var writer = new PrintWriter(new FileWriter(dataFile, true));

            for (String str : strs) {
                writer.println(str);
            }

            writer.close();
        } catch (IOException err) {

        }
    }

    /**
     * Сохраняет все данные в файл (перезаписывая его)
     * TODO: Exception
     */
    private void resaveAll() {
        try {
            var writer = new PrintWriter(new FileWriter(dataFile, false));

            for (int i = 0; i < len; i++) {
                writer.println(data[i].toString());
            }

            writer.close();
        } catch (IOException err) {

        }
    }

    /**
     * Создает массив данных (new T[])
     * @param len длина массива
     * @return массив данных
     *
     * TODO: удалить после внедрения ArrayList
     */
    private T[] getTArray(int len) {
        @SuppressWarnings("unchecked")
        var arr = (T[])Array.newInstance(dataClass, len);

        return arr;
    }

    private void initFile(String url) {
        dataFile = new File("test.txt");
    }

    /**
     * Создает файл на диске (если он еще не существует)
     * TODO: Exception
     */
    private void createFile() {
        try {
            boolean created = dataFile.createNewFile();

        } catch (IOException err) {

        }

    }

    private T parseItem(String itemStr) {
        return (T)new Integer(5);
    }
}
