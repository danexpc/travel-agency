package com.danexpc.agency.dao.impl;

import com.danexpc.agency.dao.ConnectionPool;
import com.danexpc.agency.dao.LocationDao;
import com.danexpc.agency.dao.enums.SQLState;
import com.danexpc.agency.exceptions.DaoException;
import com.danexpc.agency.exceptions.EntityNotFoundException;
import com.danexpc.agency.exceptions.UniqueViolationException;
import com.danexpc.agency.exceptions.UnprocessableEntityException;
import com.danexpc.agency.entity.LocationModel;
import com.danexpc.agency.helpers.Pagination;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.danexpc.agency.exceptions.DaoException.DAO_EXCEPTION_MESSAGE;
import static com.danexpc.agency.exceptions.EntityNotFoundException.ENTITY_NOT_FOUND_EXCEPTION_MESSAGE;
import static com.danexpc.agency.exceptions.UnprocessableEntityException.UNPROCESSABLE_ENTITY_EXCEPTION_MESSAGE;

public class LocationDaoImpl implements LocationDao {

    static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private final String CREATE_LOCATION = "INSERT INTO locations(note, address, street, city, country) VALUES (?, ?, ?, ?, ?);";

    private final String UPDATE_LOCATION = "UPDATE locations SET note=?, address=?, street=?, city=?, country=? WHERE id=?;";

    private final String FIND_LOCATION_BY_ID = "SELECT id, note, address, street, city, country FROM locations WHERE id=?;";

    private final String FIND_ALL_LOCATIONS = "SELECT id, note, address, street, city, country FROM locations limit ? offset ?;";

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
    public void create(LocationModel model) {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(CREATE_LOCATION)){
            preparedStatement.setString(1, model.getNote());
            preparedStatement.setString(2, model.getAddress());
            preparedStatement.setString(3, model.getStreet());
            preparedStatement.setString(4, model.getCity());
            preparedStatement.setString(5, model.getCountry());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            if (Objects.equals(e.getSQLState(), SQLState.UNIQUE_VIOLATION.getState())) {
                throw new UniqueViolationException(e);
            }

            throw new UnprocessableEntityException(String.format(UNPROCESSABLE_ENTITY_EXCEPTION_MESSAGE, model.toString()), e);
        }

    }

    @Override
    public void update(LocationModel model) {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_LOCATION)){
            preparedStatement.setString(1, model.getNote());
            preparedStatement.setString(2, model.getAddress());
            preparedStatement.setString(3, model.getStreet());
            preparedStatement.setString(4, model.getCity());
            preparedStatement.setString(5, model.getCountry());
            preparedStatement.setInt(6, model.getId());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            if (Objects.equals(e.getSQLState(), SQLState.UNIQUE_VIOLATION.getState())) {
                throw new UniqueViolationException(e);
            }

            throw new UnprocessableEntityException(String.format(UNPROCESSABLE_ENTITY_EXCEPTION_MESSAGE, model.toString()), e);
        }
    }

    @Override
    public LocationModel findById(Integer id) throws EntityNotFoundException {
        ResultSet resultSet;

        LocationModel resModel;

        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(FIND_LOCATION_BY_ID)) {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new EntityNotFoundException(String.format(ENTITY_NOT_FOUND_EXCEPTION_MESSAGE, id));
            }

            resModel = buildModel(resultSet);
        } catch (SQLException e) {
            throw new DaoException(DAO_EXCEPTION_MESSAGE, e);
        }

        return resModel;
    }

    @Override
    public List<LocationModel> findAll(Pagination pagination) {
        ResultSet resultSet;

        List<LocationModel> resModel = new ArrayList<>();

        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_LOCATIONS)){
            preparedStatement.setInt(1, pagination.getLimit());
            preparedStatement.setInt(2, pagination.getOffset());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                resModel.add(buildModel(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(DAO_EXCEPTION_MESSAGE, e);
        }

        return resModel;
    }

    @Override
    public void deleteById(Integer id) {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(DELETE_LOCATION_BY_ID)){
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new DaoException(DAO_EXCEPTION_MESSAGE, e);
        }
    }
}
