package com.company.simpljwt.services;

public interface CrudService<T, ID> {

    T save(T object);
}