package com.danexpc.agency.dao.impl;

import com.danexpc.agency.dao.ConnectionPool;
import com.danexpc.agency.dao.HotelDao;
import com.danexpc.agency.dao.enums.SQLState;
import com.danexpc.agency.exceptions.DaoException;
import com.danexpc.agency.exceptions.EntityNotFoundException;
import com.danexpc.agency.exceptions.UniqueViolationException;
import com.danexpc.agency.exceptions.UnprocessableEntityException;
import com.danexpc.agency.model.HotelModel;

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

public class HotelDaoImpl implements HotelDao {

    static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private final String CREATE_HOTEL = "INSERT INTO hotels(location_id, name, description, type) VALUES (?, ?, ?, ?);";

    private final String UPDATE_HOTEL = "UPDATE hotels SET location_id=?, name=?, description=?, type=? WHERE id=?;";

    private final String FIND_HOTEL_BY_ID = "SELECT id, location_id, name, description, type FROM hotels WHERE id=?;";

    private final String FIND_ALL_HOTELS = "SELECT id, location_id, name, description, type FROM hotels;";

    private final String DELETE_HOTEL_BY_ID = "DELETE FROM hotels WHERE id=?;";

    protected HotelModel buildModel(ResultSet resultSet) throws SQLException {
        HotelModel model = new HotelModel();
        model.setId(resultSet.getInt("id"));
        model.setLocationId(resultSet.getInt("location_id"));
        model.setName(resultSet.getString("name"));
        model.setDescription(resultSet.getString("description"));
        model.setType(resultSet.getInt("type"));
        return model;
    }

    @Override
    public void create(HotelModel model) {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(CREATE_HOTEL)){
            preparedStatement.setInt(1, model.getLocationId());
            preparedStatement.setString(2, model.getName());
            preparedStatement.setString(3, model.getDescription());
            preparedStatement.setInt(4, model.getType());
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
    public void update(HotelModel model) {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_HOTEL)){
            preparedStatement.setInt(1, model.getLocationId());
            preparedStatement.setString(2, model.getName());
            preparedStatement.setString(3, model.getDescription());
            preparedStatement.setInt(4, model.getType());
            preparedStatement.setInt(5, model.getId());
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
    public HotelModel findById(Integer id) throws EntityNotFoundException {
        ResultSet resultSet;

        HotelModel resModel = null;

        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(FIND_HOTEL_BY_ID)){
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
    public List<HotelModel> findAll() {
        ResultSet resultSet;

        List<HotelModel> resModel = new ArrayList<>();

        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_HOTELS)){
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
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(DELETE_HOTEL_BY_ID)){
            preparedStatement.setInt(1, id);
            preparedStatement.executeQuery();
            connection.commit();
        } catch (SQLException e) {
            throw new DaoException(DAO_EXCEPTION_MESSAGE, e);
        }
    }
}
