package com.danexpc.agency.service;

import com.danexpc.agency.dao.DaoSingletonFactory;
import com.danexpc.agency.dao.UserDao;
import com.danexpc.agency.dao.impl.DaoSingletonFactoryImpl;
import com.danexpc.agency.dto.request.UserRequestDto;
import com.danexpc.agency.dto.response.UserResponseDto;
import com.danexpc.agency.exceptions.EntityNotFoundDaoException;
import com.danexpc.agency.model.UserModel;

import java.util.List;

public class UserService {

    private final DaoSingletonFactory factory = DaoSingletonFactoryImpl.getInstance();

    private final UserDao userDao = factory.getUserDao();

    public void createUser(UserRequestDto dto) throws EntityNotFoundDaoException {
        UserModel model = new UserModel();

        model.setEmail(dto.getEmail());
        model.setPassword(dto.getPassword());
        model.setFirstName(dto.getFirstName());
        model.setLastName(dto.getLastName());
        model.setCity(dto.getCity());
        model.setType(dto.getType());
        model.setIsBlocked(dto.getIsBlocked());

        userDao.create(model);
    }

    public void updateUser(Integer id, UserRequestDto dto) {

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
