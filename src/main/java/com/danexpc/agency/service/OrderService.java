package com.danexpc.agency.service;

import com.danexpc.agency.dao.DaoSingletonFactory;
import com.danexpc.agency.dao.OrderDao;
import com.danexpc.agency.dao.impl.DaoSingletonFactoryImpl;
import com.danexpc.agency.dto.request.OrderRequestDto;
import com.danexpc.agency.dto.response.OrderResponseDto;
import com.danexpc.agency.model.OrderModel;
import lombok.SneakyThrows;

import java.util.List;
import java.util.stream.Collectors;

public class OrderService {

    private final DaoSingletonFactory factory = DaoSingletonFactoryImpl.getInstance();

    private final OrderDao orderDao = factory.getOrderDao();

    public void createOrder(OrderRequestDto dto) {
        OrderModel model = dto.buildModel();

        orderDao.create(model);
    }

    public void updateOrder(Integer id, OrderRequestDto dto) {
        OrderModel model = dto.buildModel();
        model.setId(id);

        orderDao.update(model);
    }

    @SneakyThrows
    public OrderResponseDto getOrderById(Integer id) {
        OrderModel model = orderDao.findById(id);

        return OrderResponseDto.fromModel(model);
    }

    @SneakyThrows
    public List<OrderResponseDto> getAllOrders() {
        List<OrderModel> models = orderDao.findAll();

        return models.stream().parallel().map(OrderResponseDto::fromModel).collect(Collectors.toUnmodifiableList());
    }

    public void deleteOrderById(Integer id) {
        orderDao.deleteById(id);
    }
}
