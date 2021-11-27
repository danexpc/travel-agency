package com.danexpc.agency.service;

import com.danexpc.agency.dao.DaoSingletonFactory;
import com.danexpc.agency.dao.OrderDao;
import com.danexpc.agency.dao.impl.DaoSingletonFactoryImpl;
import com.danexpc.agency.dto.OrderRequestDto;
import com.danexpc.agency.dto.OrderResponseDto;

import java.util.List;

public class OrderService {

    private final DaoSingletonFactory factory = DaoSingletonFactoryImpl.getInstance();

    private final OrderDao orderDao = factory.getOrderDao();

    public void createOrder(OrderRequestDto dto) {

    }

    public void updateOrder(OrderRequestDto dto) {

    }

    public OrderResponseDto getOrderById(Integer id) {
        return null;
    }

    public List<OrderResponseDto> getAllOrders() {
        return null;
    }

    public void deleteOrderById(Integer id) {
    }
}