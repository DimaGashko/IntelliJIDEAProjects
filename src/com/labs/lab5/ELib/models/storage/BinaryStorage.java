package com.labs.lab5.ELib.models.storage;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Класс для хранения данных на основании текстового файла
 * @param <T> класс хранимых данных
 */
public class BinaryStorage<T> implements IStorage<T> {

    // Текстовый файл для хранения данных
    private File dataFile;

    // Хранимые данные
    private ArrayList<T> data = new ArrayList<>();

    public BinaryStorage(String url) throws IOException {
        initFile(url);
        load();
    }

    @Override
    public void add(T item) throws IOException {
        save(item);
        data.add(item);
    }

    @Override
    public void addAll(T[] items) throws IOException {
        for (T item : items) {
            add(item);
        }
    }

    @Override
    public T[] getArrOfData() {
        return data.toArray();
    }

    @Override
    public ArrayList getArrOfData(Predicate<T> filter) {
        return data.stream().filter(filter);
    }

    @Override
    public void remove(T item) throws IOException {
        remove(el -> el.equals(item));
    }

    @Override
    public void remove(Predicate<T> isRemoved) throws IOException {
        T[] newData = Arrays.stream(getArrOfData())
                // Не через методы типа indexOf
                // Так как может быть несколько одинковых элементов
                .filter(el -> !isRemoved.test(el))
                .toArray(this::_getTArray);

        setData(newData);
    }

    @Override
    public void replace(T prevItem, T newItem) throws IOException {
        for (int i = 0; i < data.length; i++) {
            if (!data[i].equals(prevItem)) continue;

            data[i] = newItem;
            break;
        }

        resaveAll();
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
     * Сохраняет переданный элемент в текстовом файле
     * @param item сохраняемый элемент
     */
    private void save(T item) throws IOException {
        _writeToFile(stringify.call(item), false);
    }

    /**
     * Устанавливает новые данные (старые удаляются)
     * @param items новые данные
     */
    private void setData(T[] items) throws IOException{
        T[] prevData = data;
        int prevLen = len;

        setCleanDataArr();

        len = items.length;
        System.arraycopy(items, 0, data, 0, len);

        try {
            resaveAll();

        } catch(IOException err) {
            data = prevData;
            len = prevLen;

            throw err;
        }
    }

    /**
     * Сохраняет все данные в файл (перезаписывая его)
     */
    private void resaveAll() throws IOException {
        var strItems = Arrays.stream(getArrOfData())
                .map(T::toString)
                .toArray(String[]::new);

        _writeToFile(strItems, true);
    }

    /**
     * Удаляет все данные из массивов (не влияет на данные в файле)
     * TODO: удалить после внедрения ArrayList
     */
    private void setCleanDataArr() {
        data = _getTArray(bufferSize);
        len = 0;
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
     * Загружает данные из текстового файла в массив данных
     */
    private void load() throws IOException {
        dataFile.createNewFile();



    }

    /**
     * Записывает в файл строку
     *
     * @param str   строка для записи
     * @param clean очистить перед записью (либо записывать в конец)
     */
    private void _writeToFile(String str, boolean clean) throws IOException {
        String[] strs = {str};
        _writeToFile(strs, clean);
    }

    /**
     * Записывает в файл строки
     *
     * @param strs  массив строк для записи
     * @param clean запись в конец файла
     */
    private void _writeToFile(String[] strs, boolean clean) throws IOException {
        dataFile.createNewFile();

        try(var fr = new FileWriter(dataFile, !clean);
            var writer = new PrintWriter(fr)
        ) {

            if (clean) {
                writer.print("");
            }

            for (String str : strs) {
                writer.println(str);
            }

        }
    }

    private void initFile(String url) {
        dataFile = new File(url);

        // Создает родительские каталоги
        dataFile.getParentFile().mkdirs();
    }

    private void setDataClass(Class<T> dataClass) {
        this.dataClass = dataClass;
    }

    private void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    public void setStringify(StringifyFunction<T> stringify) {
        this.stringify = stringify;
    }

    public void setParse(ParseFunction<T> parse) {
        this.parse = parse;
    }
}
