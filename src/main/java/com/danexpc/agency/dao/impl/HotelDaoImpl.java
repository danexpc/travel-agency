package com.danexpc.agency.dao.impl;

import com.danexpc.agency.dao.ConnectionPool;
import com.danexpc.agency.dao.HotelDao;
import com.danexpc.agency.exceptions.EntityNotFoundDaoException;
import com.danexpc.agency.model.HotelModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HotelDaoImpl implements HotelDao {

    static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private final String CREATE_HOTEL = "INSERT INTO hotels(location_id, name, description, type) VALUES (?, ?, ?, ?);";

    private final String UPDATE_HOTEL = "UPDATE hotels SET location_id=?, name=?, description=?, type=? WHERE id=?;";

    private final String FIND_HOTEL_BY_ID = "SELECT id, location_id, name, description, type FROM hotels WHERE id=?;";

    private final String FIND_ALL_HOTELS = "SELECT id, location_id, name, description, type FROM hotels;";

    private final String DELETE_HOTEL_BY_ID = "DELETE FROM hotels WHERE id=?;";

    protected HotelModel buildHotelModel(ResultSet resultSet) throws SQLException {
        HotelModel model = new HotelModel();
        model.setId(resultSet.getInt("id"));
        model.setLocationId(resultSet.getInt("location_id"));
        model.setName(resultSet.getString("name"));
        model.setDescription(resultSet.getString("description"));
        model.setType(resultSet.getInt("type"));
        return model;
    }

    @Override
    public HotelModel create(HotelModel model) throws EntityNotFoundDaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        HotelModel resModel = null;

        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(CREATE_HOTEL);
            preparedStatement.setInt(1, model.getLocationId());
            preparedStatement.setString(2, model.getName());
            preparedStatement.setString(3, model.getDescription());
            preparedStatement.setInt(4, model.getType());
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new EntityNotFoundDaoException();
            }

            resModel = buildHotelModel(resultSet);
        } catch (SQLException e) {
            // todo log
            connectionPool.rollback(connection);
            // todo throw exception
        } finally {
            connectionPool.commitAndClose(connection, preparedStatement, resultSet);
        }

        return resModel;
    }

    @Override
    public HotelModel update(HotelModel model) throws EntityNotFoundDaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        HotelModel resModel = null;

        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_HOTEL);
            preparedStatement.setInt(1, model.getLocationId());
            preparedStatement.setString(2, model.getName());
            preparedStatement.setString(3, model.getDescription());
            preparedStatement.setInt(4, model.getType());
            preparedStatement.setInt(5, model.getId());
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new EntityNotFoundDaoException();
            }

            resModel = buildHotelModel(resultSet);
        } catch (SQLException e) {
            // todo log
            connectionPool.rollback(connection);
            // todo throw exception
        } finally {
            connectionPool.commitAndClose(connection, preparedStatement, resultSet);
        }

        return resModel;
    }

    @Override
    public HotelModel findById(Integer id) throws EntityNotFoundDaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        HotelModel resModel = null;

        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(FIND_HOTEL_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new EntityNotFoundDaoException();
            }

            resModel = buildHotelModel(resultSet);
        } catch (SQLException e) {
            // todo log
            connectionPool.rollback(connection);
            // todo throw exception
        } finally {
            connectionPool.commitAndClose(connection, preparedStatement, resultSet);
        }

        return resModel;
    }

    @Override
    public List<HotelModel> findAll() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<HotelModel> resModel = new ArrayList<>();

        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(FIND_ALL_HOTELS);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                resModel.add(buildHotelModel(resultSet));
            }
        } catch (SQLException e) {
            // todo log
            connectionPool.rollback(connection);
            // todo throw exception
        } finally {
            connectionPool.commitAndClose(connection, preparedStatement, resultSet);
        }

        return resModel;
    }

    @Override
    public HotelModel deleteById(Integer id) throws EntityNotFoundDaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        HotelModel resModel = null;

        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_HOTEL_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new EntityNotFoundDaoException();
            }

            resModel = buildHotelModel(resultSet);
        } catch (SQLException e) {
            // todo log
            connectionPool.rollback(connection);
            // todo throw exception
        } finally {
            connectionPool.commitAndClose(connection, preparedStatement, resultSet);
        }

        return resModel;
    }
}
