package com.danexpc.agency.service;

import com.danexpc.agency.builder.NotificationDtoBuilder;
import com.danexpc.agency.dto.notification.NotificationDto;
import com.danexpc.agency.dto.request.PaymentRequestDto;
import com.danexpc.agency.dto.response.OrderResponseDto;
import com.danexpc.agency.rmq.NotificationsSender;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

public class NotificationService {

    private final OrderService orderService = new OrderService();

    @SneakyThrows
    public void sendMessage(PaymentRequestDto dto) {
        OrderResponseDto order = orderService.getOrderById(dto.getOrderId());
        NotificationDto notificationDto = NotificationDtoBuilder.build(dto, order);

        ObjectMapper objectMapper = new ObjectMapper();
        String notification = objectMapper.writeValueAsString(notificationDto);

        NotificationsSender.send(notification);
    }
}
