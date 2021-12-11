package com.danexpc.agency.dao.impl;

import com.danexpc.agency.dao.ConnectionPool;
import com.danexpc.agency.dao.ScheduleDao;
import com.danexpc.agency.exceptions.EntityNotFoundException;
import com.danexpc.agency.exceptions.UnprocessableEntityException;
import com.danexpc.agency.model.ScheduleModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ScheduleDaoImpl implements ScheduleDao {

    static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private final String CREATE_SCHEDULE = "INSERT INTO schedules(tour_id, start_location_id, hotel_id, price, departure_date," +
            " duration, is_on_fire, total_places_qty, remaining_places_qty)" +
            " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

    private final String UPDATE_SCHEDULE = "UPDATE schedules SET tour_id=?, start_location_id=?, hotel_id=?, price=?, departure_date=?," +
            " duration=?, is_on_fire=?, total_places_qty=?, remaining_places_qty=? WHERE id=?;";

    private final String FIND_SCHEDULE_BY_ID = "SELECT id, tour_id, start_location_id, hotel_id, price, departure_date," +
            " duration, is_on_fire, total_places_qty, remaining_places_qty FROM schedules WHERE id=?;";

    private final String FIND_ALL_SCHEDULES = "SELECT id, tour_id, start_location_id, hotel_id, price, departure_date," +
            " duration, is_on_fire, total_places_qty, remaining_places_qty FROM schedules;";

    private final String DELETE_SCHEDULE_BY_ID = "DELETE FROM schedules WHERE id=?;";

    protected ScheduleModel buildModel(ResultSet resultSet) throws SQLException {
        ScheduleModel model = new ScheduleModel();
        model.setId(resultSet.getInt("id"));
        model.setTourId(resultSet.getInt("tour_id"));
        model.setStartLocationId(resultSet.getInt("start_location_id"));
        model.setHotelId(resultSet.getInt("hotel_id"));
        model.setDepartureDate(resultSet.getObject("departure_time", LocalDateTime.class));
        model.setDuration(resultSet.getLong("duration"));
        model.setIsOnFire(resultSet.getBoolean("is_on_fire"));
        model.setTotalPlaceQty(resultSet.getInt("total_places_qty"));
        model.setRemainingPlacesQty(resultSet.getInt("remaining_places_qty"));
        return model;
    }

    @Override
    public void create(ScheduleModel model) {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(CREATE_SCHEDULE)) {
            preparedStatement.setInt(1, model.getTourId());
            preparedStatement.setInt(2, model.getStartLocationId());
            preparedStatement.setInt(3, model.getHotelId());
            preparedStatement.setBigDecimal(4, model.getPrice());
            preparedStatement.setTimestamp(5, Timestamp.valueOf(model.getDepartureDate()));
            preparedStatement.setLong(6, model.getDuration());
            preparedStatement.setBoolean(7, model.getIsOnFire());
            preparedStatement.setInt(8, model.getTotalPlaceQty());
            preparedStatement.setInt(9, model.getRemainingPlacesQty());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new UnprocessableEntityException();
        }

    }

    @Override
    public void update(ScheduleModel model) {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SCHEDULE)) {
            preparedStatement.setInt(1, model.getTourId());
            preparedStatement.setInt(2, model.getStartLocationId());
            preparedStatement.setInt(3, model.getHotelId());
            preparedStatement.setBigDecimal(4, model.getPrice());
            preparedStatement.setTimestamp(5, Timestamp.valueOf(model.getDepartureDate()));
            preparedStatement.setLong(6, model.getDuration());
            preparedStatement.setBoolean(7, model.getIsOnFire());
            preparedStatement.setInt(8, model.getTotalPlaceQty());
            preparedStatement.setInt(9, model.getRemainingPlacesQty());
            preparedStatement.setInt(10, model.getId());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new UnprocessableEntityException();
        }
    }

    @Override
    public ScheduleModel findById(Integer id) throws EntityNotFoundException {
        ResultSet resultSet;

        ScheduleModel resModel = null;

        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(FIND_SCHEDULE_BY_ID)) {
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
    public List<ScheduleModel> findAll() {
        ResultSet resultSet;

        List<ScheduleModel> resModel = new ArrayList<>();

        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SCHEDULES)) {
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
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SCHEDULE_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            // todo log
        }
    }
}
