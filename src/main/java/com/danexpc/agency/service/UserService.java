package com.danexpc.agency.service;

import com.danexpc.agency.dao.DaoSingletonFactory;
import com.danexpc.agency.dao.UserDao;
import com.danexpc.agency.dao.impl.DaoSingletonFactoryImpl;
import com.danexpc.agency.dto.request.UserRequestDto;
import com.danexpc.agency.dto.response.UserResponseDto;
import com.danexpc.agency.entity.UserModel;
import com.danexpc.agency.helpers.Pagination;
import lombok.SneakyThrows;

import java.util.List;
import java.util.stream.Collectors;

public class UserService {

    private final DaoSingletonFactory factory = DaoSingletonFactoryImpl.getInstance();

    private final UserDao userDao = factory.getUserDao();

    public void createUser(UserRequestDto dto) {
        UserModel model = dto.buildModel();
        userDao.create(model);
    }

    public void updateUser(Integer id, UserRequestDto dto) {
        UserModel model = dto.buildModel();
        model.setId(id);
        userDao.update(model);
    }

    @SneakyThrows
    public UserResponseDto getUserById(Integer id) {
        UserModel model = userDao.findById(id);
        return UserResponseDto.fromModel(model);
    }

    @SneakyThrows
    public List<UserResponseDto> getAllUsers(Pagination pagination) {
        List<UserModel> models = userDao.findAll(pagination);

        return models.stream().parallel().map(UserResponseDto::fromModel).collect(Collectors.toUnmodifiableList());
    }

    public void deleteUserById(Integer id) {
        userDao.deleteById(id);
    }
}
