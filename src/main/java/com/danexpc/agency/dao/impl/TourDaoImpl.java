package com.danexpc.agency.dao.impl;

import com.danexpc.agency.dao.ConnectionPool;
import com.danexpc.agency.dao.TourDao;
import com.danexpc.agency.exceptions.EntityNotFoundException;
import com.danexpc.agency.model.TourModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TourDaoImpl implements TourDao {

    static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private final String CREATE_TOUR = "INSERT INTO tours(name, description, type) VALUES (?, ?, ?);";

    private final String UPDATE_TOUR = "UPDATE tours SET name=?, description=?, type=? WHERE id=?;";

    private final String FIND_TOUR_BY_ID = "SELECT id, name, description, type FROM tours WHERE id=?;";

    private final String FIND_ALL_TOURS = "SELECT id, name, description, type FROM tours;";

    private final String DELETE_TOUR_BY_ID = "DELETE FROM tours WHERE id=?;";

    protected TourModel buildModel(ResultSet resultSet) throws SQLException {
        TourModel model = new TourModel();
        model.setId(resultSet.getInt("id"));
        model.setName(resultSet.getString("name"));
        model.setDescription(resultSet.getString("description"));
        model.setType(resultSet.getInt("type"));
        return model;
    }

    @Override
    public void create(TourModel model) {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(CREATE_TOUR)){
            preparedStatement.setString(1, model.getName());
            preparedStatement.setString(2, model.getDescription());
            preparedStatement.setInt(3, model.getType());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            // todo log
        }

    }

    @Override
    public void update(TourModel model) {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TOUR)){
            preparedStatement.setString(1, model.getName());
            preparedStatement.setString(2, model.getDescription());
            preparedStatement.setInt(3, model.getType());
            preparedStatement.setInt(4, model.getId());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            // todo log
        }
    }

    @Override
    public TourModel findById(Integer id) throws EntityNotFoundException {
        ResultSet resultSet;

        TourModel resModel = null;

        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(FIND_TOUR_BY_ID)){
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
    public List<TourModel> findAll() {
        ResultSet resultSet;

        List<TourModel> resModel = new ArrayList<>();

        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_TOURS)){
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
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TOUR_BY_ID)){
            preparedStatement.setInt(1, id);
            preparedStatement.executeQuery();
            connection.commit();
        } catch (SQLException e) {
            // todo log
        }
    }
}
