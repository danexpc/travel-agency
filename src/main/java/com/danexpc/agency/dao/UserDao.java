package com.danexpc.agency.dao;

import com.danexpc.agency.exceptions.EntityNotFoundException;
import com.danexpc.agency.entity.UserModel;

public interface UserDao extends AbstractDao<UserModel, Integer> {

    UserModel findByEmailAndPassword(String email, String password) throws EntityNotFoundException;
}
