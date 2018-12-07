package com.labs.lab5.ELib.models.storage;
import com.labs.lab3.part1.library.Book;

/**
 * Класс для хранения данных на основании текстового файла
 * @param <T> класс хранимых данных
 */
public class TextStorage<T> implements IStorage {
    // Максимально количество хранимых элементов
    private static final int DEF_BUFFER_SIZE = 100;

    // Хранимые данные
    public T[] data;

    public TextStorage() {
        initDataArray(DEF_BUFFER_SIZE);
    }

    public TextStorage(int bufferSize) {
        initDataArray(bufferSize);
    }

    @Override
    public boolean add(Object item) {
        return false;
    }

    @Override
    public boolean addAll(Object[] item) {
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
     * Инициализирует массив для хранения данных
     * @param bufferSize размер массива данных
     */
    private void initDataArray(int bufferSize) {
        @SuppressWarnings("unchecked")
        T[] arr = (T[])new Book[bufferSize];

        data = arr;
    }
}
