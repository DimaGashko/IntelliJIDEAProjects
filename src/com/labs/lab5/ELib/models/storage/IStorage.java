package com.labs.lab5.ELib.models.storage;

import java.io.IOException;
import java.util.function.Predicate;

/**
 * Интерфейс хранения данных
 * Обеспечивает загрузку, получение, добавление... данных
 * @param <T> класс хранимых данных
 */
public interface IStorage<T> {
    /**
     * Добавляет элемент в хранилище
     * @param item добавляемый элемент
     * @return удалось ли добавить элемент
     */
    void add(T item) throws IOException;

    /**
     * Добавляет в хранилище массив переданных элементов
     * @param items массив элементов
     */
    void addAll(T[] items) throws IOException;

    /**
     * @return данных в виде массива
     */
    T[] getArrOfData();

    /**
     * Возвращает данные (в виде массива), что удовлетворяют придекат filter
     * @param filter выражение для фильтрации данных
     * @return данные (в виде массива), что удовлетворяют предикат filter
     */
    T[] getArrOfData(Predicate<T> filter);

    /**
     * Удаляет из хранилища все элементы, что "equals" к item
     * @param item удаляемый элемент
     * @return удалось ли удаление
     * @throws IOException
     */
    void remove(T item) throws IOException;

    /**
     * Удаляет все элементы что удовлетворяют предикату isRemoved
     * @param isRemoved нужно ли удалять элемент
     * @return удалось ли удаление
     * @throws IOException
     */
    void remove(Predicate<T> isRemoved) throws IOException;

    /**
     * Заменяет prevItem на newItem
     * @param prevItem заменяемый элемент
     * @param newItem на что нужно заменить prevItem
     * @throws IOException
     */
    void replace(T prevItem, T newItem) throws IOException;
}
