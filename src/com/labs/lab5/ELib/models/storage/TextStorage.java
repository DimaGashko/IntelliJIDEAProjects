package com.labs.lab5.ELib.models.storage;

import com.labs.lab3.part1.library.Book;

import java.io.*;
import java.lang.reflect.Array;
import java.net.URL;
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

    //Путь к текстовому файлу для хранения данных
    private URL url;

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
        setUrl(url);
    }

    /**
     * TODO: после внедрения ArrayList убрать аргумент dataClass
     */
    public TextStorage(String url, int bufferSize, Class dataClass) {
        this.dataClass = dataClass;

        this.data = getTArray(bufferSize);
        setUrl(url);
    }

    @Override
    public boolean add(T item) {
        if (len == data.length) {
            return false;
        }

        data[len++] = item;

        return save(item);
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
            var br = new BufferedReader(new FileReader(url.toString()));



            br.close();
        } catch (IOException err) {

        }

        return true;
    }

    /**
     * Сохраняет переданный элемент в текстовом файле
     * @return true если сохранение было успешным
     */
    private boolean save(T item) {
        try {
            var bw = new BufferedWriter(new FileWriter(url.toString()));



            bw.close();
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

    private void setUrl(String url) {
        this.url = getClass().getResource(url);
    }
}
