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

    private final String CREATE_ORDER = "INSERT INTO orders(user_id, tour_id, status, discount, final_price) VALUES (?, ?, ?, ?, ?);";

    private final String UPDATE_ORDER = "UPDATE orders SET user_id=?, tour_id=?, status=?, discount=?, final_price=? WHERE id=?;";

    private final String FIND_ORDER_BY_ID = "SELECT id, user_id, tour_id, status, discount, final_price FROM orders WHERE id=?;";

    private final String FIND_ALL_ORDERS = "SELECT id, user_id, tour_id, status, discount, final_price FROM orders;";

    private final String DELETE_ORDER_BY_ID = "DELETE FROM orders WHERE id=?;";

    protected OrderModel buildModel(ResultSet resultSet) throws SQLException {
        OrderModel model = new OrderModel();
        model.setId(resultSet.getInt("id"));
        model.setUserId(resultSet.getInt("user_id"));
        model.setTourId(resultSet.getInt("tour_id"));
        model.setOrderStatus(resultSet.getInt("status"));
        model.setDiscount(resultSet.getFloat("discount"));
        model.setFinalPrice(resultSet.getBigDecimal("final_price"));
        return model;
    }

    @Override
    public void create(OrderModel model) throws EntityNotFoundDaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        OrderModel resModel = null;

        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(CREATE_ORDER);
            preparedStatement.setInt(1, model.getUserId());
            preparedStatement.setInt(2, model.getTourId());
            preparedStatement.setInt(3, model.getOrderStatus());
            preparedStatement.setFloat(4, model.getDiscount());
            preparedStatement.setBigDecimal(5, model.getFinalPrice());
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
    public OrderModel update(OrderModel model) throws EntityNotFoundDaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        OrderModel resModel = null;

        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_ORDER);
            preparedStatement.setInt(1, model.getUserId());
            preparedStatement.setInt(2, model.getTourId());
            preparedStatement.setInt(3, model.getOrderStatus());
            preparedStatement.setFloat(4, model.getDiscount());
            preparedStatement.setBigDecimal(5, model.getFinalPrice());
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
    public OrderModel findById(Integer id) throws EntityNotFoundDaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        OrderModel resModel = null;

        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(FIND_ORDER_BY_ID);
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
    public List<OrderModel> findAll() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<OrderModel> resModel = new ArrayList<>();

        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(FIND_ALL_ORDERS);
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
    public OrderModel deleteById(Integer id) throws EntityNotFoundDaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        OrderModel resModel = null;

        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_ORDER_BY_ID);
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
