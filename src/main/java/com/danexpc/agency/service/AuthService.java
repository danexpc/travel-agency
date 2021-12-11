package com.danexpc.agency.service;

import com.danexpc.agency.dao.DaoSingletonFactory;
import com.danexpc.agency.dao.UserDao;
import com.danexpc.agency.dao.impl.DaoSingletonFactoryImpl;
import com.danexpc.agency.dto.request.UserLoginRequest;
import com.danexpc.agency.dto.response.UserLoginResponse;
import com.danexpc.agency.model.UserModel;
import com.danexpc.agency.security.JWTUtil;

public class AuthService {

    private final DaoSingletonFactory factory = DaoSingletonFactoryImpl.getInstance();

    private final UserDao userDao = factory.getUserDao();

    public UserLoginResponse login(UserLoginRequest dto) {
        UserModel user = userDao.findByEmailAndPassword(dto.getEmail(), dto.getPassword());

        return new UserLoginResponse(
                user.getId(),
                user.getType(),
                JWTUtil.generateAccessToken(user.getId(), user.getType()),
                JWTUtil.generateRefreshToken(user.getId(), user.getType())
        );
    }
}
