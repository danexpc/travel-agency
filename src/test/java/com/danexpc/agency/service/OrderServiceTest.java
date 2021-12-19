package com.danexpc.agency.service;

import com.danexpc.agency.dao.DaoSingletonFactory;
import com.danexpc.agency.dao.OrderDao;
import com.danexpc.agency.dao.impl.DaoSingletonFactoryImpl;
import com.danexpc.agency.dto.request.OrderRequestDto;
import com.danexpc.agency.dto.response.OrderResponseDto;
import com.danexpc.agency.entity.OrderModel;
import com.danexpc.agency.helpers.Pagination;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class OrderServiceTest {

    DaoSingletonFactory factory = mock(DaoSingletonFactory.class);
    OrderDao orderDao = mock(OrderDao.class);

    @Test
    void testGetOrder() {
        Integer id = 1;
        Integer userId = 2;
        Integer scheduleId = 3;
        Integer orderStatus = 1;
        Float discount = 0.5f;
        BigDecimal finalPrice = BigDecimal.valueOf(1000);

        try (MockedStatic<DaoSingletonFactoryImpl> mocked = mockStatic(DaoSingletonFactoryImpl.class)) {
            mocked.when(DaoSingletonFactoryImpl::getInstance).thenReturn(factory);

            when(factory.getOrderDao()).thenReturn(
                    orderDao
            );

            OrderService orderService = new OrderService();

            OrderModel model = new OrderModel(
                    id, userId, scheduleId, orderStatus, discount, finalPrice
            );

            when(orderDao.findById(anyInt())).thenReturn(
                    model
            );

            OrderResponseDto expectedDto = OrderResponseDto.fromModel(model);


            OrderResponseDto actualDto = orderService.getOrderById(id);

            assertEquals(expectedDto, actualDto);
        }
    }

    @Test
    void testGetOrders() {
        List<OrderModel> expected = List.of(new OrderModel(), new OrderModel());

        try (MockedStatic<DaoSingletonFactoryImpl> mocked = mockStatic(DaoSingletonFactoryImpl.class)) {
            mocked.when(DaoSingletonFactoryImpl::getInstance).thenReturn(factory);

            when(factory.getOrderDao()).thenReturn(
                    orderDao
            );

            OrderService orderService = new OrderService();

            when(orderDao.findAll(any(Pagination.class))).thenReturn(
                    expected
            );

            List<OrderResponseDto> actualDtos = orderService.getAllOrders(new Pagination(10, 0));

            assertEquals(expected.size(), actualDtos.size());
        }
    }

    @Test
    void testCreateOrder() {
        Integer userId = 2;
        Integer scheduleId = 3;
        Integer orderStatus = 1;
        Float discount = 0.5f;
        BigDecimal finalPrice = BigDecimal.valueOf(1000);

        OrderRequestDto request = new OrderRequestDto(userId, scheduleId, orderStatus, discount, finalPrice);

        OrderModel expectedModel = request.buildModel();

        try (MockedStatic<DaoSingletonFactoryImpl> mocked = mockStatic(DaoSingletonFactoryImpl.class)) {
            mocked.when(DaoSingletonFactoryImpl::getInstance).thenReturn(factory);

            when(factory.getOrderDao()).thenReturn(
                    orderDao
            );

            OrderService orderService = new OrderService();

            doNothing().when(orderDao).create(expectedModel);

            orderService.createOrder(request);

            verify(orderDao, times(1)).create(expectedModel);
        }
    }

    @Test
    void testUpdateOrder() {
        Integer id = 1;
        Integer userId = 2;
        Integer scheduleId = 3;
        Integer orderStatus = 1;
        Float discount = 0.5f;
        BigDecimal finalPrice = BigDecimal.valueOf(1000);

        OrderRequestDto request = new OrderRequestDto(userId, scheduleId, orderStatus, discount, finalPrice);

        OrderModel expectedModel = request.buildModel();
        expectedModel.setId(id);

        try (MockedStatic<DaoSingletonFactoryImpl> mocked = mockStatic(DaoSingletonFactoryImpl.class)) {
            mocked.when(DaoSingletonFactoryImpl::getInstance).thenReturn(factory);

            when(factory.getOrderDao()).thenReturn(
                    orderDao
            );

            OrderService orderService = new OrderService();

            doNothing().when(orderDao).update(expectedModel);

            orderService.updateOrder(id, request);

            verify(orderDao, times(1)).update(expectedModel);
        }
    }

    @Test
    void testDeleteOrder() {
        Integer id = 1;

        try (MockedStatic<DaoSingletonFactoryImpl> mocked = mockStatic(DaoSingletonFactoryImpl.class)) {
            mocked.when(DaoSingletonFactoryImpl::getInstance).thenReturn(factory);

            when(factory.getOrderDao()).thenReturn(
                    orderDao
            );

            OrderService orderService = new OrderService();

            doNothing().when(orderDao).deleteById(id);

            orderService.deleteOrderById(id);

            verify(orderDao, times(1)).deleteById(id);
        }
    }
}
