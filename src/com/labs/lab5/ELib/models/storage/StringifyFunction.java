package com.labs.lab5.ELib.models.storage;

/**
 * Функция преобразования объекта в строку
 * @param <T> тип объекта
 */
@FunctionalInterface
public interface StringifyFunction<T> {
    String call(T item);
}