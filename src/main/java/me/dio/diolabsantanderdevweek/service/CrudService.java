package me.dio.diolabsantanderdevweek.service;

import java.util.List;

public interface CrudService<ID, T> {
    T findById(ID id);

    T create(T t);

    T update(ID id, T t);

    void delete(ID id);

    List<T> findAll();
}
