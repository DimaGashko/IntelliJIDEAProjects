package com.labs.lab5.ELib.models.storage;

import java.io.*;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.function.Predicate;


/**
 * Функция преобразования объекта в строку
 * @param <T> тип объекта
 */
@FunctionalInterface
interface StringifyFunction<T> {
    String stringify(T item);
}

/**
 * Функция преобразования строки в объект
 * @param <T> тип объекта
 */
@FunctionalInterface
interface ParseFunction<T> {
    T parse(String strT);
}

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

    private StringifyFunction stringify;
    private ParseFunction parse;

    /**
     * TODO: после внедрения ArrayList убрать аргумент dataClass
     */
    public TextStorage(String url, StringifyFunction stringify, ParseFunction parse, Class dataClass) {
        setDataClass(dataClass);
        setStringify(stringify);
        setParse(parse);

        this.data = _getTArray(DEF_BUFFER_SIZE);
        initFile(url);

        load();
    }

    @Override
    public boolean add(T item) {
        addToArr(item);
        return save(item);
    }

    @Override
    public T[] getArrOfData() {
        return Arrays.copyOf(data, len);
    }

    @Override
    public T[] getArrOfData(Predicate<T> filter) {
        return Arrays.stream(getArrOfData())
                .filter(filter)
                .toArray(this::_getTArray);
    }

    @Override
    public boolean remove(T item) {
        return true;
    }

    /**
     * Добавляет элемент данных в массив данных
     * @param item добавляемый массив
     * TODO: убрать после внедрения ArrayList
     */
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
                addToArr(parseItem(itemStr));
            }

            reader.close();

        } catch (IOException err) {
            return false;
        }

        return true;
    }

    /**
     * Сохраняет переданный элемент в текстовом файле
     *
     * @param item сохраняемый элемент
     */
    private boolean save(T item) {
        return _writeStrToFile(, true);
    }

    /**
     * Сохраняет все данные в файл (перезаписывая его)
     */
    private boolean resaveAll() {
        var strItems = Arrays.stream(getArrOfData())
                .map(T::toString)
                .toArray(String[]::new);

        return _writeArrStrToFile(strItems, false);
    }

    private void initFile(String url) {
        dataFile = new File("test.txt");
    }

    /**
     * Создает файл на диске (если он еще не существует)
     * TODO: Exception
     */
    private boolean createFile() {
        try {
            return dataFile.createNewFile();

        } catch (IOException err) {
            return false;
        }

    }

    private T parseItem(String itemStr) {
        return (T) new Integer(5);
    }

    /**
     * Создает массив данных (new T[])
     *
     * @param len длина массива
     * @return массив данных
     * <p>
     * TODO: удалить после внедрения ArrayList
     */
    private T[] _getTArray(int len) {
        @SuppressWarnings("unchecked")
        var arr = (T[]) Array.newInstance(dataClass, len);

        return arr;
    }

    /**
     * Записывает в файл строку
     *
     * @param str    строка для записи
     * @param append запись в конец файла
     */
    private boolean _writeStrToFile(String str, boolean append) {
        try {
            var writer = new PrintWriter(new FileWriter(dataFile, append));
            writer.println(str);
            writer.close();

        } catch (IOException err) {
            return false;
        }

        return true;
    }

    private void setDataClass(Class dataClass) {
        this.dataClass = dataClass;
    }

    public void setStringify(StringifyFunction stringify) {
        this.stringify = stringify;
    }

    public void setParse(ParseFunction parse) {
        this.parse = parse;
    }

    /**
     * Записывает в файл строки
     *
     * @param strs   массив строк для записи
     * @param append запись в конец файла
     */
    private boolean _writeArrStrToFile(String[] strs, boolean append) {
        try {
            var writer = new PrintWriter(new FileWriter(dataFile, append));

            for (String str : strs) {
                writer.println(str);
            }

            writer.close();

        } catch (IOException err) {
            return false;
        }

        return true;
    }

}
