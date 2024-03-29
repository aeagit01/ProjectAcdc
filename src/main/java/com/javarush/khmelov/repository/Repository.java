package com.javarush.khmelov.repository;

import com.javarush.khmelov.entity.User;

import java.util.Collection;
import java.util.Optional;

public interface Repository<T> {

    Collection<T> getAll();

    T get(long id);

    void create(T entity);

    void update(T entity);

    void delete(T entity);
}
