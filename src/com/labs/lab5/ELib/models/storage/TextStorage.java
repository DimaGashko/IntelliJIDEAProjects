package com.labs.lab5.ELib.models.storage;

import org.jetbrains.annotations.Contract;

/**
 * Класс для хранения данных на основании текстового файла
 * @param <T> класс хранимых данных
 * @version 0.0.0.1
 *
 * TODO: заменить Array на ArrayList
 */
public class TextStorage<T> implements IStorage {
    // Массив хранимых данных
    private T[] data;

    // Количество данных
    private int len = 0;

    /**
     * TODO: после внедрения ArrayList удалить либо сделать публичным
     */
    private TextStorage() {

    }

    /**
     * @param dataArray массив для хранения данных
     *
     * TODO: после внедрения ArrayList удалить этот конструктор
     */
    public TextStorage(T[] dataArray) {
        data = dataArray;
    }

    @Override
    public boolean add(Object item) {
        return false;
    }

    @Override
    public Object[] getArrOfData() {
        return new Object[0];
    }

    @Override
    public Object[] getArrOfData(IFilter filter) {
        return new Object[0];
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
    private boolean save() {
        return true;
    }
}
