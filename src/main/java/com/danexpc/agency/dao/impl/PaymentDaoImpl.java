package com.danexpc.agency.dao.impl;

import com.danexpc.agency.dao.ConnectionPool;
import com.danexpc.agency.dao.PaymentDao;
import com.danexpc.agency.dao.enums.SQLState;
import com.danexpc.agency.entity.PaymentModel;
import com.danexpc.agency.exceptions.DaoException;
import com.danexpc.agency.exceptions.EntityNotFoundException;
import com.danexpc.agency.exceptions.UniqueViolationException;
import com.danexpc.agency.exceptions.UnprocessableEntityException;
import com.danexpc.agency.helpers.Pagination;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.danexpc.agency.exceptions.DaoException.DAO_EXCEPTION_MESSAGE;
import static com.danexpc.agency.exceptions.EntityNotFoundException.ENTITY_NOT_FOUND_EXCEPTION_MESSAGE;
import static com.danexpc.agency.exceptions.UnprocessableEntityException.UNPROCESSABLE_ENTITY_EXCEPTION_MESSAGE;

public class PaymentDaoImpl implements PaymentDao {

    static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private final String CREATE_PAYMENT = "INSERT INTO payments(order_id, payment, date, currency_type, exchange_rate) VALUES (?, ?, ?, ?, ?);";

    private final String UPDATE_PAYMENT = "UPDATE payments SET order_id=?, payment=?, date=?, currency_type=?, exchange_rate=? WHERE id=?;";

    private final String FIND_PAYMENT_BY_ID = "SELECT id, order_id, payment, date, currency_type, exchange_rate FROM payments WHERE id=?;";

    private final String FIND_ALL_PAYMENTS = "SELECT id, order_id, payment, date, currency_type, exchange_rate FROM payments limit ? offset ?;";

    private final String DELETE_PAYMENT_BY_ID = "DELETE FROM payments WHERE id=?;";

    protected PaymentModel buildModel(ResultSet resultSet) throws SQLException {
        PaymentModel model = new PaymentModel();
        model.setId(resultSet.getInt("id"));
        model.setOrderId(resultSet.getInt("order_id"));
        model.setPayment(resultSet.getBigDecimal("payment"));
        model.setDate(resultSet.getObject("date", LocalDateTime.class));
        model.setCurrencyType(resultSet.getString("currency_type"));
        model.setExchangeRate(resultSet.getDouble("exchange_rate"));
        return model;
    }

    @Override
    public void create(PaymentModel model) {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(CREATE_PAYMENT)) {
            preparedStatement.setInt(1, model.getOrderId());
            preparedStatement.setBigDecimal(2, model.getPayment());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(model.getDate()));
            preparedStatement.setString(4, model.getCurrencyType());
            preparedStatement.setDouble(5, model.getExchangeRate());
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
    public void update(PaymentModel model) {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PAYMENT)) {
            preparedStatement.setInt(1, model.getOrderId());
            preparedStatement.setBigDecimal(2, model.getPayment());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(model.getDate()));
            preparedStatement.setString(4, model.getCurrencyType());
            preparedStatement.setDouble(5, model.getExchangeRate());
            preparedStatement.setDouble(6, model.getId());
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
    public PaymentModel findById(Integer id) throws EntityNotFoundException {
        ResultSet resultSet;

        PaymentModel resModel;

        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(FIND_PAYMENT_BY_ID)) {
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
    public List<PaymentModel> findAll(Pagination pagination) throws EntityNotFoundException {
        ResultSet resultSet;

        List<PaymentModel> resModel = new ArrayList<>();

        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_PAYMENTS)) {
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
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PAYMENT_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new DaoException(DAO_EXCEPTION_MESSAGE, e);
        }
    }
}
