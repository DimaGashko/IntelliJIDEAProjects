package com.labs.lab5.ELib.models.storage;

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
    //Путь к текстовому файлу для хранения данных
    private String src;

    // Массив хранимых данных
    private T[] data;

    // Количество данных
    private int len = 0;

    /**
     * @param dataArray массив для хранения данных
     *
     * TODO: после внедрения ArrayList убрать атрибут dataArray
     */
    public TextStorage(String src, T[] dataArray) {
        this.src = src;

        data = dataArray;
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
    public boolean remove(T item) {
        return false;
    }

    /**
     * Загружает данные из текстового файла в массив данных
     * @return true если загрузка удалась
     */
    private boolean load() {
        return true;
    }

    /**
     * Сохраняет переданный элемент в текстовом файле
     * @return true если сохранение было успешным
     */
    private boolean save(T item) {
        return true;
    }
}
