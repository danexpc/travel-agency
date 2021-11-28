package com.danexpc.agency.dao.impl;

import com.danexpc.agency.dao.ConnectionPool;
import com.danexpc.agency.dao.LocationDao;
import com.danexpc.agency.exceptions.EntityNotFoundDaoException;
import com.danexpc.agency.model.LocationModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LocationDaoImpl implements LocationDao {

    static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private final String CREATE_LOCATION = "INSERT INTO locations(note, address, street, city, country) VALUES (?, ?, ?, ?, ?);";

    private final String UPDATE_LOCATION = "UPDATE locations SET note=?, address=?, street=?, city=?, country=? WHERE id=?;";

    private final String FIND_LOCATION_BY_ID = "SELECT id, note, address, street, city, country FROM locations WHERE id=?;";

    private final String FIND_ALL_LOCATIONS = "SELECT id, note, address, street, city, country FROM locations;";

    private final String DELETE_LOCATION_BY_ID = "DELETE FROM locations WHERE id=?;";

    protected LocationModel buildModel(ResultSet resultSet) throws SQLException {
        LocationModel model = new LocationModel();
        model.setId(resultSet.getInt("id"));
        model.setNote(resultSet.getString("note"));
        model.setAddress(resultSet.getString("address"));
        model.setStreet(resultSet.getString("street"));
        model.setCity(resultSet.getString("city"));
        model.setCountry(resultSet.getString("country"));
        return model;
    }

    @Override
    public void create(LocationModel model) throws EntityNotFoundDaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        LocationModel resModel = null;

        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(CREATE_LOCATION);
            preparedStatement.setString(1, model.getNote());
            preparedStatement.setString(2, model.getAddress());
            preparedStatement.setString(3, model.getStreet());
            preparedStatement.setString(4, model.getCity());
            preparedStatement.setString(5, model.getCountry());
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new EntityNotFoundDaoException();
            }

            resModel = buildModel(resultSet);
        } catch (SQLException e) {
            // todo log
            connectionPool.rollback(connection);
            // todo throw exception
        } finally {
            connectionPool.commitAndClose(connection, preparedStatement, resultSet);
        }

    }

    @Override
    public LocationModel update(LocationModel model) throws EntityNotFoundDaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        LocationModel resModel = null;

        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_LOCATION);
            preparedStatement.setString(1, model.getNote());
            preparedStatement.setString(2, model.getAddress());
            preparedStatement.setString(3, model.getStreet());
            preparedStatement.setString(4, model.getCity());
            preparedStatement.setString(5, model.getCountry());
            preparedStatement.setInt(6, model.getId());
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new EntityNotFoundDaoException();
            }

            resModel = buildModel(resultSet);
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
    public LocationModel findById(Integer id) throws EntityNotFoundDaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        LocationModel resModel = null;

        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(FIND_LOCATION_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new EntityNotFoundDaoException();
            }

            resModel = buildModel(resultSet);
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
    public List<LocationModel> findAll() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<LocationModel> resModel = new ArrayList<>();

        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(FIND_ALL_LOCATIONS);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                resModel.add(buildModel(resultSet));
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
    public LocationModel deleteById(Integer id) throws EntityNotFoundDaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        LocationModel resModel = null;

        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_LOCATION_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new EntityNotFoundDaoException();
            }

            resModel = buildModel(resultSet);
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
