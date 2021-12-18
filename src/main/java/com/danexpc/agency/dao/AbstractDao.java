package com.danexpc.agency.dao;

import com.danexpc.agency.exceptions.EntityNotFoundException;
import com.danexpc.agency.helpers.Pagination;

import java.util.List;

public interface AbstractDao<T, ID> {

    void create(T model);

    void update(T model);

    T findById(ID id) throws EntityNotFoundException;

    List<T> findAll(Pagination pagination) throws EntityNotFoundException;

    void deleteById(ID id);
}
