package com.danexpc.agency.dao.impl;

import com.danexpc.agency.dao.ConnectionPool;
import com.danexpc.agency.dao.OrderDao;
import com.danexpc.agency.exceptions.EntityNotFoundDaoException;
import com.danexpc.agency.model.OrderModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

    static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private final String CREATE_ORDER = "INSERT INTO orders(user_id, schedule_id, status, discount, final_price) VALUES (?, ?, ?, ?, ?);";

    private final String UPDATE_ORDER = "UPDATE orders SET user_id=?, schedule_id=?, status=?, discount=?, final_price=? WHERE id=?;";

    private final String FIND_ORDER_BY_ID = "SELECT id, user_id, schedule_id, status, discount, final_price FROM orders WHERE id=?;";

    private final String FIND_ALL_ORDERS = "SELECT id, user_id, schedule_id, status, discount, final_price FROM orders;";

    private final String DELETE_ORDER_BY_ID = "DELETE FROM orders WHERE id=?;";

    protected OrderModel buildModel(ResultSet resultSet) throws SQLException {
        OrderModel model = new OrderModel();
        model.setId(resultSet.getInt("id"));
        model.setUserId(resultSet.getInt("user_id"));
        model.setScheduleId(resultSet.getInt("schedule_id"));
        model.setOrderStatus(resultSet.getInt("status"));
        model.setDiscount(resultSet.getFloat("discount"));
        model.setFinalPrice(resultSet.getBigDecimal("final_price"));
        return model;
    }

    @Override
    public void create(OrderModel model) {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(CREATE_ORDER)) {
            preparedStatement.setInt(1, model.getUserId());
            preparedStatement.setInt(2, model.getScheduleId());
            preparedStatement.setInt(3, model.getOrderStatus());
            preparedStatement.setFloat(4, model.getDiscount());
            preparedStatement.setBigDecimal(5, model.getFinalPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // todo log
        }

    }

    @Override
    public void update(OrderModel model) {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDER)) {
            preparedStatement.setInt(1, model.getUserId());
            preparedStatement.setInt(2, model.getScheduleId());
            preparedStatement.setInt(3, model.getOrderStatus());
            preparedStatement.setFloat(4, model.getDiscount());
            preparedStatement.setBigDecimal(5, model.getFinalPrice());
            preparedStatement.setInt(6, model.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // todo log
        }
    }

    @Override
    public OrderModel findById(Integer id) throws EntityNotFoundDaoException {
        ResultSet resultSet;

        OrderModel resModel = null;

        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(FIND_ORDER_BY_ID)) {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new EntityNotFoundDaoException();
            }

            resModel = buildModel(resultSet);
        } catch (SQLException e) {
            // todo log
        }

        return resModel;
    }

    @Override
    public List<OrderModel> findAll() {
        ResultSet resultSet;

        List<OrderModel> resModel = new ArrayList<>();

        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_ORDERS)) {
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
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ORDER_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // todo log
        }
    }
}
