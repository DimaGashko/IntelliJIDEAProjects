package com.labs.lab5.ELib.models.storage;

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
    boolean add(T item);

    /**
     * Добавляет в хранилище массив переданных элементов
     * @param items массив элементов
     */
    void addAll(T[] items);

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
     */
    boolean remove(T item);

    /**
     * Удаляет все элементы что удовлетворяют предикату isRemoved
     * @param isRemoved нужно ли удалять элемент
     * @return удалось ли удаление
     */
    boolean remove(Predicate<T> isRemoved);
}
