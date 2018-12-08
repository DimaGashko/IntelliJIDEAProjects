package com.labs.lab5.ELib.models.storage;

/**
 * Функция преобразования строки в объект
 * @param <T> тип объекта
 */
@FunctionalInterface
public interface ParseFunction<T> {
    T call(String strT);
}