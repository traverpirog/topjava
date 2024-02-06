package ru.javawebinar.topjava.dao;

import java.util.List;

public interface CrudDao<T> {
    List<T> getAll();
    T get(int id);
    T create(T t);
    void update(T t);
    void delete(int id);
}
