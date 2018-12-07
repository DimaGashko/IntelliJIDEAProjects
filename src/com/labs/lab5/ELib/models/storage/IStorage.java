package com.labs.lab5.ELib.models.storage;

/**
 *
 * @param <T>
 */
@FunctionalInterface
interface IFilter<T> {
    boolean filter(T item);
}

public interface IStorage<T> {
    void load();
    boolean add(T item);
    boolean addAll(T[] item);

    T[] getArrOfData();
    T[] getArrOfData(IFilter<T> filter);
}
