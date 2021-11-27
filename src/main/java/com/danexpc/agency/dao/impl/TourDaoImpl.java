package com.danexpc.agency.dao.impl;

import com.danexpc.agency.dao.ConnectionPool;
import com.danexpc.agency.dao.TourDao;
import com.danexpc.agency.exceptions.EntityNotFoundDaoException;
import com.danexpc.agency.model.TourModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TourDaoImpl implements TourDao {

    static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private final String CREATE_TOUR = "INSERT INTO tours(start_location_id, hotel_id, name, description, type, price, departure_date," +
            " duration, active, is_on_fire, total_places_qty, remaining_places_qty)" +
            " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    private final String UPDATE_TOUR = "UPDATE tours SET start_location_id=?, hotel_id=?, name=?, description=?, type=?, price=?, departure_date=?," +
            " duration=?, active=?, is_on_fire=?, total_places_qty=?, remaining_places_qty=? WHERE id=?;";

    private final String FIND_TOUR_BY_ID = "SELECT id, start_location_id, hotel_id, name, description, type, price, departure_date," +
            " duration, active, is_on_fire, total_places_qty, remaining_places_qty FROM tours WHERE id=?;";

    private final String FIND_ALL_TOURS = "SELECT id, start_location_id, hotel_id, name, description, type, price, departure_date," +
            " duration, active, is_on_fire, total_places_qty, remaining_places_qty FROM tours;";

    private final String DELETE_TOUR_BY_ID = "DELETE FROM tours WHERE id=?;";

    protected TourModel buildModel(ResultSet resultSet) throws SQLException {
        TourModel model = new TourModel();
        model.setId(resultSet.getInt("id"));
        model.setStartLocationId(resultSet.getInt("start_location_id"));
        model.setHotelId(resultSet.getInt("hotel_id"));
        model.setName(resultSet.getString("name"));
        model.setDescription(resultSet.getString("description"));
        model.setDepartureDate(resultSet.getObject("departure_time", LocalDateTime.class));
        model.setDuration(resultSet.getLong("duration"));
        model.setIsActive(resultSet.getBoolean("active"));
        model.setIsOnFire(resultSet.getBoolean("is_on_fire"));
        model.setTotalPlaceQty(resultSet.getInt("total_places_qty"));
        model.setRemainingPlacesQty(resultSet.getInt("remaining_places_qty"));
        return model;
    }

    @Override
    public TourModel create(TourModel model) throws EntityNotFoundDaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        TourModel resModel = null;

        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(CREATE_TOUR);
            preparedStatement.setInt(1, model.getStartLocationId());
            preparedStatement.setInt(2, model.getHotelId());
            preparedStatement.setString(3, model.getName());
            preparedStatement.setString(4, model.getDescription());
            preparedStatement.setInt(5, model.getType());
            preparedStatement.setBigDecimal(6, model.getPrice());
            preparedStatement.setTimestamp(7, Timestamp.valueOf(model.getDepartureDate()));
            preparedStatement.setLong(8, model.getDuration());
            preparedStatement.setBoolean(9, model.getIsActive());
            preparedStatement.setBoolean(10, model.getIsOnFire());
            preparedStatement.setInt(11, model.getTotalPlaceQty());
            preparedStatement.setInt(12, model.getRemainingPlacesQty());
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
    public TourModel update(TourModel model) throws EntityNotFoundDaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        TourModel resModel = null;

        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_TOUR);
            preparedStatement.setInt(1, model.getStartLocationId());
            preparedStatement.setInt(2, model.getHotelId());
            preparedStatement.setString(3, model.getName());
            preparedStatement.setString(4, model.getDescription());
            preparedStatement.setInt(5, model.getType());
            preparedStatement.setBigDecimal(6, model.getPrice());
            preparedStatement.setTimestamp(7, Timestamp.valueOf(model.getDepartureDate()));
            preparedStatement.setLong(8, model.getDuration());
            preparedStatement.setBoolean(9, model.getIsActive());
            preparedStatement.setBoolean(10, model.getIsOnFire());
            preparedStatement.setInt(11, model.getTotalPlaceQty());
            preparedStatement.setInt(12, model.getRemainingPlacesQty());
            preparedStatement.setInt(13, model.getId());
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
    public TourModel findById(Integer id) throws EntityNotFoundDaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        TourModel resModel = null;

        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(FIND_TOUR_BY_ID);
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
    public List<TourModel> findAll() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<TourModel> resModel = new ArrayList<>();

        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(FIND_ALL_TOURS);
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
    public TourModel deleteById(Integer id) throws EntityNotFoundDaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        TourModel resModel = null;

        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_TOUR_BY_ID);
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
