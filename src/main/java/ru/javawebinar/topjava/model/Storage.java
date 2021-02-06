package ru.javawebinar.topjava.model;

import java.util.List;
import java.util.function.Predicate;

public interface Storage<T> {
    void add(T t);

    void update(T t);

    void delete(int id);

    Meal get(int id);

    List<T> getFiltered(Predicate<T> p);
}
