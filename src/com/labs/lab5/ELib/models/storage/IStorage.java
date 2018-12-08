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
     * @return данных в виде массива
     */
    T[] getArrOfData();

    /**
     * Возвращает данные (в виде массива), что удовлетворяют фильтр
     * @param filter лямбда выражение для фильтрации данных
     * @return данные (в виде массива), что удовлетворяют фильтр
     */
    T[] getArrOfData(Predicate<T> filter);

    /**
     * Удаляет из хранилища все элементы, что equals к item
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
