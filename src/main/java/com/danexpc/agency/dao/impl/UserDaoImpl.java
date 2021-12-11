package com.danexpc.agency.dao.impl;

import com.danexpc.agency.dao.ConnectionPool;
import com.danexpc.agency.dao.UserDao;
import com.danexpc.agency.exceptions.EntityNotFoundException;
import com.danexpc.agency.exceptions.UnprocessableEntityException;
import com.danexpc.agency.model.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private final String CREATE_USER = "INSERT INTO users(email, password, first_name, last_name, city, is_blocked, type) VALUES (?, ?, ?, ?, ?, ?, ?);";

    private final String UPDATE_USER = "UPDATE users SET email=?, password=?, first_name=?, last_name=?, city=?, is_blocked=?, type=? WHERE id=?;";

    private final String FIND_USER_BY_ID = "SELECT id, email, password, first_name, last_name, city, is_blocked, type FROM users WHERE id=?;";

    private final String FIND_ALL_USERS = "SELECT id, email, password, first_name, last_name, city, is_blocked, type FROM users;";

    private final String DELETE_USER_BY_ID = "DELETE FROM orders WHERE id=?;";

    protected UserModel buildModel(ResultSet resultSet) throws SQLException {
        UserModel model = new UserModel();
        model.setId(resultSet.getInt("id"));
        model.setEmail(resultSet.getString("email"));
        model.setPassword(resultSet.getString("password"));
        model.setFirstName(resultSet.getString("first_name"));
        model.setLastName(resultSet.getString("last_name"));
        model.setCity(resultSet.getString("city"));
        model.setIsBlocked(resultSet.getBoolean("is_blocked"));
        model.setType(resultSet.getInt("type"));
        return model;
    }

    @Override
    public void create(UserModel model) {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER)) {
            preparedStatement.setString(1, model.getEmail());
            preparedStatement.setString(2, model.getPassword());
            preparedStatement.setString(3, model.getFirstName());
            preparedStatement.setString(4, model.getLastName());
            preparedStatement.setString(5, model.getCity());
            preparedStatement.setBoolean(6, model.getIsBlocked());
            preparedStatement.setInt(7, model.getType());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new UnprocessableEntityException();
        }
    }

    @Override
    public void update(UserModel model) {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER)) {
            preparedStatement.setString(1, model.getEmail());
            preparedStatement.setString(2, model.getPassword());
            preparedStatement.setString(3, model.getFirstName());
            preparedStatement.setString(4, model.getLastName());
            preparedStatement.setString(5, model.getCity());
            preparedStatement.setBoolean(6, model.getIsBlocked());
            preparedStatement.setInt(7, model.getType());
            preparedStatement.setInt(8, model.getId());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new UnprocessableEntityException();
        }
    }

    @Override
    public UserModel findById(Integer id) throws EntityNotFoundException {
        ResultSet resultSet;

        UserModel resModel = null;

        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_ID)){
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new EntityNotFoundException();
            }

            resModel = buildModel(resultSet);
        } catch (SQLException e) {
            // todo log
        }

        return resModel;
    }

    @Override
    public List<UserModel> findAll() {
        ResultSet resultSet;

        List<UserModel> resModel = new ArrayList<>();

        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_USERS)){
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                resModel.add(buildModel(resultSet));
            }
        } catch (SQLException e) {
            // todo log
        }

        return resModel;
    }

    @Override
    public void deleteById(Integer id) {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_BY_ID)){
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            // todo log
        }
    }
}
