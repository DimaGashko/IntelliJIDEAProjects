package com.labs.lab5.ELib.models.storage;

public class TextStorage<T> implements IStorage {

    @Override
    public void load() {

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
}
