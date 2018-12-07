package com.labs.lab5.ELib.models.storage;

import java.io.*;
import java.lang.reflect.Array;
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
        createFile(url);
    }

    /**
     * TODO: после внедрения ArrayList убрать аргумент dataClass
     */
    public TextStorage(String url, int bufferSize, Class dataClass) {
        this.dataClass = dataClass;

        this.data = getTArray(bufferSize);
        createFile(url);
    }

    @Override
    public boolean add(T item) {
        if (len == data.length) {
            return false;
        }

        data[len++] = item;

        save(item);
        return true;
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

    /**
     * Загружает данные из текстового файла в массив данных
     * @return true если загрузка удалась
     */
    private boolean load() {
        try {
            var reader = new BufferedReader(new FileReader(dataFile));

            reader.close();
        } catch (IOException err) {

        }

        return true;
    }

    /**
     * Сохраняет переданный элемент в текстовом файле
     * @return true если сохранение было успешным
     */
    private void save(T item) {
        try {
            var writer = new PrintWriter(new FileWriter(dataFile, true));
            writer.println(item.toString());
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

    private void createFile(String url) {
        dataFile = new File("test.txt");

        try {
            boolean created = dataFile.createNewFile();

        } catch (IOException err) {

        }

    }
}
