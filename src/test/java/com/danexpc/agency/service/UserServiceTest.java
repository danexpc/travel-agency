package com.danexpc.agency.service;

import com.danexpc.agency.dao.DaoSingletonFactory;
import com.danexpc.agency.dao.UserDao;
import com.danexpc.agency.dao.impl.DaoSingletonFactoryImpl;
import com.danexpc.agency.dto.request.UserRequestDto;
import com.danexpc.agency.dto.response.UserResponseDto;
import com.danexpc.agency.entity.UserModel;
import com.danexpc.agency.helpers.Pagination;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    DaoSingletonFactory factory = mock(DaoSingletonFactory.class);
    UserDao userDao = mock(UserDao.class);

    @Test
    void testGetUser() {
        Integer id = 1;
        String email = "testEmail";
        String password = "testPassword";
        String firstName = "testFirstName";
        String lastName = "testLastName";
        String city = "testCity";
        Integer type = 3;

        try (MockedStatic<DaoSingletonFactoryImpl> mocked = mockStatic(DaoSingletonFactoryImpl.class)) {
            mocked.when(DaoSingletonFactoryImpl::getInstance).thenReturn(factory);

            when(factory.getUserDao()).thenReturn(
                    userDao
            );

            UserService userService = new UserService();

            UserModel model = new UserModel(
                    id, email, password,
                    firstName, lastName,
                    city, false, type
            );

            when(userDao.findById(anyInt())).thenReturn(
                    model
            );

            UserResponseDto expectedDto = UserResponseDto.fromModel(model);


            UserResponseDto actualDto = userService.getUserById(id);

            assertEquals(expectedDto, actualDto);
        }
    }

    @Test
    void testGetUsers() {
        List<UserModel> users = List.of(new UserModel(), new UserModel());

        try (MockedStatic<DaoSingletonFactoryImpl> mocked = mockStatic(DaoSingletonFactoryImpl.class)) {
            mocked.when(DaoSingletonFactoryImpl::getInstance).thenReturn(factory);

            when(factory.getUserDao()).thenReturn(
                    userDao
            );

            UserService userService = new UserService();

            when(userDao.findAll(any(Pagination.class))).thenReturn(
                    users
            );

            List<UserResponseDto> actualDtos = userService.getAllUsers(new Pagination(10, 0));

            assertEquals(users.size(), actualDtos.size());
        }
    }

    @Test
    void testCreateUser() {
        String email = "testEmail";
        String password = "testPassword";
        String firstName = "testFirstName";
        String lastName = "testLastName";
        String city = "testCity";
        Integer type = 3;

        UserRequestDto request = new UserRequestDto(email, password, firstName, lastName, city, false, type);

        UserModel expectedModel = request.buildModel();

        try (MockedStatic<DaoSingletonFactoryImpl> mocked = mockStatic(DaoSingletonFactoryImpl.class)) {
            mocked.when(DaoSingletonFactoryImpl::getInstance).thenReturn(factory);

            when(factory.getUserDao()).thenReturn(
                    userDao
            );

            UserService userService = new UserService();

            doNothing().when(userDao).create(expectedModel);

            userService.createUser(request);

            verify(userDao, times(1)).create(expectedModel);
        }
    }

    @Test
    void testUpdateUser() {
        Integer id = 1;
        String email = "testEmail";
        String password = "testPassword";
        String firstName = "testFirstName";
        String lastName = "testLastName";
        String city = "testCity";
        Integer type = 3;

        UserRequestDto request = new UserRequestDto(email, password, firstName, lastName, city, false, type);

        UserModel expectedModel = request.buildModel();
        expectedModel.setId(id);

        try (MockedStatic<DaoSingletonFactoryImpl> mocked = mockStatic(DaoSingletonFactoryImpl.class)) {
            mocked.when(DaoSingletonFactoryImpl::getInstance).thenReturn(factory);

            when(factory.getUserDao()).thenReturn(
                    userDao
            );

            UserService userService = new UserService();

            doNothing().when(userDao).update(expectedModel);

            userService.updateUser(id, request);

            verify(userDao, times(1)).update(expectedModel);
        }
    }

    @Test
    void testDeleteUser() {
        Integer id = 1;

        try (MockedStatic<DaoSingletonFactoryImpl> mocked = mockStatic(DaoSingletonFactoryImpl.class)) {
            mocked.when(DaoSingletonFactoryImpl::getInstance).thenReturn(factory);

            when(factory.getUserDao()).thenReturn(
                    userDao
            );

            UserService userService = new UserService();

            doNothing().when(userDao).deleteById(id);

            userService.deleteUserById(id);

            verify(userDao, times(1)).deleteById(id);
        }
    }
}
