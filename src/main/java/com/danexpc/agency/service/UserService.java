package com.danexpc.agency.service;

import com.danexpc.agency.dao.DaoSingletonFactory;
import com.danexpc.agency.dao.UserDao;
import com.danexpc.agency.dao.impl.DaoSingletonFactoryImpl;
import com.danexpc.agency.dto.UserRequestDto;
import com.danexpc.agency.dto.UserResponseDto;

import java.util.List;

public class UserService {

    private final DaoSingletonFactory factory = DaoSingletonFactoryImpl.getInstance();

    private final UserDao userDao = factory.getUserDao();

    public void createUser(UserRequestDto dto) {

    }

    public void updateUser(UserRequestDto dto) {

    }

    public UserResponseDto getUserById(Integer id) {
        return null;
    }

    public List<UserResponseDto> getAllUsers() {
        return null;
    }

    public void deleteUserById(Integer id) {
    }
}
