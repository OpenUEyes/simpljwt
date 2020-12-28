package com.company.simpljwt.services;

import java.util.Optional;

public interface CrudService<T, ID> {

    T save(T object);

    Optional<T> findById(ID id);
}