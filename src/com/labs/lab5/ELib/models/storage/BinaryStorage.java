package com.labs.lab5.ELib.models.storage;

import com.labs.lab3.part1.library.Book;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Класс для хранения данных на основании текстового файла
 * @param <T> класс хранимых данных
 */
public class BinaryStorage<T> implements IStorage<T> {

    //Текстовый файл для хранения данных
    private File dataFile;

    // Класс хранимих данных
    private Class<T> dataClass;

    // Массив хранимых данных
    private ArrayList<T> data = new ArrayList<>();

    public BinaryStorage(String url, Class<T> dataClass) throws IOException {
        setDataClass(dataClass);
        initFile(url);

        load();
    }

    @Override
    public void add(T item) throws IOException {
        save(item, true);
        data.add(item);
    }

    @Override
    public void addAll(T[] items) throws IOException {
        for (T item : items) {
            add(item);
        }
    }

    @Override
    public T[] getArrOfData() {
        return data.toArray(_getTArray(data.size()));
    }

    @Override
    public T[] getArrOfData(Predicate<T> filter) {
        return Arrays.stream(getArrOfData())
                .filter(filter)
                .toArray(this::_getTArray);
    }

    @Override
    public void remove(T item) throws IOException {
        remove(el -> el.equals(item));
    }

    @Override
    public void remove(Predicate<T> isRemoved) throws IOException {
        T[] newData = Arrays.stream(getArrOfData())
                // Не через методы типа indexOf
                // Так как может быть несколько одинковых элементов
                .filter(el -> !isRemoved.test(el))
                .toArray(this::_getTArray);

        //setData(newData);
    }

    @Override
    public void replace(T prevItem, T newItem) throws IOException {
        data.stream().map(item -> {
            return (item.equals(prevItem)) ? newItem : item;


        });

        save(data, false);
    }

    /**
     * Сохраняет переданный элемент в бинарном файле
     * @param item сохраняемый элемент
     */
    private void save(T item, boolean append) throws IOException {

        try (FileOutputStream fos = new FileOutputStream(dataFile, append);
             ObjectOutputStream out = new ObjectOutputStream(fos)) {

            out.writeObject(item);
        }
    }

    /**
     * Сохраняет переданные элементы в бинарном файле
     * @param items сохраняемые элементы
     */
    private void save(ArrayList<T> items, boolean append) throws IOException {

        try (FileOutputStream fos = new FileOutputStream(dataFile, append);
             ObjectOutputStream out = new ObjectOutputStream(fos)) {

            out.writeObject(items);
        }
    }

    /**
     * Создает массив данных (new T[])
     *
     * @param len длина массива
     * @return массив данных
     * <p>
     * TODO: удалить после внедрения ArrayList
     */
    private T[] _getTArray(int len) {
        @SuppressWarnings("unchecked")
        var arr = (T[]) Array.newInstance(dataClass, len);

        return arr;
    }

    /**
     * Загружает данные из текстового файла в массив данных
     */
    private void load() throws IOException {
        //dataFile.createNewFile();

        try(FileInputStream fis = new FileInputStream("test");
            ObjectInputStream ois = new ObjectInputStream(fis)) {

            ArrayList<T> loaded = (ArrayList<T>)ois.readObject();
            if (loaded != null) data = loaded;

        } catch (ClassNotFoundException err) {

        }

        System.out.println(data);
    }

    private void initFile(String url) {
        dataFile = new File(url);

        // Создает родительские каталоги
        dataFile.getParentFile().mkdirs();
    }

    private void setDataClass(Class<T> dataClass) {
        this.dataClass = dataClass;
    }

}