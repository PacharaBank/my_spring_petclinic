package com.example.demo.services;

import java.util.List;

public interface CrudService<T, ID> {
    List<T> findAll();
    T findById(ID id);
    void delete(T object);
    void deleteByID(ID id);
    T save(T object);
}
