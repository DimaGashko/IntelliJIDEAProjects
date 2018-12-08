package com.labs.lab5.ELib.models.storage;

import org.jetbrains.annotations.NotNull;

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

    private int bufferSize;

    //Текстовый файл для хранения данных
    private File dataFile;

    // Класс хранимих данных
    // Используется для создания new T[]
    // TODO: убрать после внедрения ArrayList
    private Class<T> dataClass;

    // Массив хранимых данных
    private T[] data;

    // Количество данных
    private int len = 0;

    private StringifyFunction<T> stringify;
    private ParseFunction<T> parse;

    /**
     * TODO: после внедрения ArrayList убрать аргумент dataClass
     */
    public TextStorage(String url, StringifyFunction<T> stringify, ParseFunction<T> parse, Class<T> dataClass) {
        setBufferSize(DEF_BUFFER_SIZE);

        setDataClass(dataClass);
        setStringify(stringify);
        setParse(parse);

        this.data = _getTArray(bufferSize);
        initFile(url);

        load();
    }

    @Override
    public boolean add(T item) {
        boolean saved = save(item);

        if (saved) {
            addToArr(item);
        }

        return saved;
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
        return remove(el -> el.equals(item));
    }

    @Override
    public boolean remove(Predicate<T> isRemoved) {
        T[] newData = Arrays.stream(getArrOfData())
                // Не через методы типа indexOf
                // Так как может быть несколько одинковых элементов
                .filter(el -> !isRemoved.test(el))
                .toArray(this::_getTArray);

        return setData(newData);
    }

    /**
     * Добавл8яет элемент данных в массив данных
     * @param item добавляемый массив
     * TODO: убрать после внедрения ArrayList
     */
    private void addToArr(T item) {
        if (len == data.length) return;
        data[len++] = item;
    }

    /**
     * Загружает данные из текстового файла в массив данных
     * TODO: Exception
     * TODO: parseErrorsCheck
     */
    private void load() {
        String itemStr;

        try {
            var reader = new BufferedReader(new FileReader(dataFile));

            while ((itemStr = reader.readLine()) != null) {
                addToArr(parse.call(itemStr));
            }

            reader.close();

        } catch (IOException err) {

        }

    }

    /**
     * Сохраняет переданный элемент в текстовом файле
     *
     * @param item сохраняемый элемент
     */
    private boolean save(T item) {
        return _writeStrToFile(stringify.call(item), true);
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

    public void setStringify(StringifyFunction<T> stringify) {
        this.stringify = stringify;
    }

    public void setParse(ParseFunction<T> parse) {
        this.parse = parse;
    }

    private void initFile(String url) {
        dataFile = new File("test.txt");
    }

    private void setDataClass(Class<T> dataClass) {
        this.dataClass = dataClass;
    }

    private void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    /**
     * Устанавливает новые данные (старые удаляются)
     * @param items новые данные
     * @return удалось ли установить данные
     */
    private boolean setData(T[] items) {
        T[] prevData = data;

        //data = _getTArray(bufferSize);
        //System.arraycopy( items, 0, data, 0, items.length);

        boolean saved = resaveAll();
/*
        if (!saved) {
            data = prevData;
            return false;
        }*/

        return true;
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
        createFile();

        try {
            var writer = new PrintWriter(new FileWriter(dataFile, append));
            writer.println(str);
            writer.close();

        } catch (IOException err) {
            return false;
        }

        return true;
    }

    /**
     * Записывает в файл строки
     *
     * @param strs   массив строк для записи
     * @param append запись в конец файла
     */
    private boolean _writeArrStrToFile(@NotNull String[] strs, boolean append) {
        createFile();

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

    /**
     * Создает файл на диске (если он еще не существует)
     * TODO: Exception
     */
    private void createFile() {
        try {
            dataFile.createNewFile();

        } catch (IOException err) {
           System.out.println(err.getMessage());
        }

    }

}
