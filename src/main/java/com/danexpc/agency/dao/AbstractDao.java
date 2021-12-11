package com.danexpc.agency.dao;

import com.danexpc.agency.exceptions.EntityNotFoundException;

import java.util.List;

public interface AbstractDao<T, ID> {

    void create(T model);

    void update(T model);

    T findById(ID id) throws EntityNotFoundException;

    List<T> findAll() throws EntityNotFoundException;

    void deleteById(ID id);
}
