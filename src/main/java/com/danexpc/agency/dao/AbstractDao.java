package com.danexpc.agency.dao;

import com.danexpc.agency.exceptions.EntityNotFoundDaoException;

import java.util.List;

public interface AbstractDao<T, ID> {

    T create(T model) throws EntityNotFoundDaoException;

    T update(T model) throws EntityNotFoundDaoException;

    T findById(ID id) throws EntityNotFoundDaoException;

    List<T> findAll() throws EntityNotFoundDaoException;

    T deleteById(ID id) throws EntityNotFoundDaoException;
}
