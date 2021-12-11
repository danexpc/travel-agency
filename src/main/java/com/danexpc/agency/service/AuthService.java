package com.danexpc.agency.service;

import com.danexpc.agency.constants.UserRole;
import com.danexpc.agency.dao.DaoSingletonFactory;
import com.danexpc.agency.dao.UserDao;
import com.danexpc.agency.dao.impl.DaoSingletonFactoryImpl;
import com.danexpc.agency.dto.request.UserAuthRequest;
import com.danexpc.agency.dto.response.UserLoginResponse;
import com.danexpc.agency.entity.UserModel;
import com.danexpc.agency.exceptions.EntityAlreadyExistsException;
import com.danexpc.agency.exceptions.EntityNotFoundException;
import com.danexpc.agency.security.JWTUtil;

public class AuthService {

    private final DaoSingletonFactory factory = DaoSingletonFactoryImpl.getInstance();

    private final UserDao userDao = factory.getUserDao();

    public UserLoginResponse login(UserAuthRequest dto) {
        UserModel user = userDao.findByEmailAndPassword(dto.getEmail(), dto.getPassword());

        return new UserLoginResponse(
                user.getId(),
                user.getType(),
                JWTUtil.generateAccessToken(user.getId(), user.getType()),
                JWTUtil.generateRefreshToken(user.getId(), user.getType())
        );
    }

    public void register(UserAuthRequest dto) {
        try {
            userDao.findByEmail(dto.getEmail());
            throw new EntityAlreadyExistsException();
        } catch (EntityNotFoundException ignored) {
            var user = new UserModel();
            user.setEmail(dto.getEmail());
            user.setPassword(dto.getPassword());
            user.setType(UserRole.CLIENT.getId());
            user.setIsBlocked(false);
            userDao.create(user);
        }

    }
}
