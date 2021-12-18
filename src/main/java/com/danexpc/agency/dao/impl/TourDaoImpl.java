package com.danexpc.agency.dao.impl;

import com.danexpc.agency.dao.ConnectionPool;
import com.danexpc.agency.dao.TourDao;
import com.danexpc.agency.dao.enums.SQLState;
import com.danexpc.agency.exceptions.DaoException;
import com.danexpc.agency.exceptions.EntityNotFoundException;
import com.danexpc.agency.exceptions.UniqueViolationException;
import com.danexpc.agency.exceptions.UnprocessableEntityException;
import com.danexpc.agency.entity.TourModel;
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

public class TourDaoImpl implements TourDao {

    static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private final String CREATE_TOUR = "INSERT INTO tours(name, description, type) VALUES (?, ?, ?);";

    private final String UPDATE_TOUR = "UPDATE tours SET name=?, description=?, type=? WHERE id=?;";

    private final String FIND_TOUR_BY_ID = "SELECT id, name, description, type FROM tours WHERE id=?;";

    private final String FIND_ALL_TOURS = "SELECT id, name, description, type FROM tours limit ? offset ?;";

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
            if (Objects.equals(e.getSQLState(), SQLState.UNIQUE_VIOLATION.getState())) {
                throw new UniqueViolationException(e);
            }

            throw new UnprocessableEntityException(String.format(UNPROCESSABLE_ENTITY_EXCEPTION_MESSAGE, model.toString()), e);
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
            if (Objects.equals(e.getSQLState(), SQLState.UNIQUE_VIOLATION.getState())) {
                throw new UniqueViolationException(e);
            }

            throw new UnprocessableEntityException(String.format(UNPROCESSABLE_ENTITY_EXCEPTION_MESSAGE, model.toString()), e);
        }
    }

    @Override
    public TourModel findById(Integer id) throws EntityNotFoundException {
        ResultSet resultSet;

        TourModel resModel;

        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(FIND_TOUR_BY_ID)){
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
    public List<TourModel> findAll(Pagination pagination) {
        ResultSet resultSet;

        List<TourModel> resModel = new ArrayList<>();

        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_TOURS)){
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
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TOUR_BY_ID)){
            preparedStatement.setInt(1, id);
            preparedStatement.executeQuery();
            connection.commit();
        } catch (SQLException e) {
            throw new DaoException(DAO_EXCEPTION_MESSAGE, e);
        }
    }
}
