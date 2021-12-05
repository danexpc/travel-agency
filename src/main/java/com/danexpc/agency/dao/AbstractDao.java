package com.danexpc.agency.dao;

import com.danexpc.agency.exceptions.EntityNotFoundDaoException;

import java.util.List;

public interface AbstractDao<T, ID> {

    void create(T model) throws EntityNotFoundDaoException;

    void update(T model) throws EntityNotFoundDaoException;

    T findById(ID id) throws EntityNotFoundDaoException;

    List<T> findAll() throws EntityNotFoundDaoException;

    void deleteById(ID id) throws EntityNotFoundDaoException;
}
