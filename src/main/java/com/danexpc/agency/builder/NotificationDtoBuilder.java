package com.danexpc.agency.builder;

import com.danexpc.agency.dto.notification.NotificationDto;
import com.danexpc.agency.dto.notification.PaymentNotificationDto;
import com.danexpc.agency.dto.request.PaymentRequestDto;
import com.danexpc.agency.dto.response.OrderResponseDto;

public class NotificationDtoBuilder {

    public static NotificationDto build(PaymentRequestDto dto, OrderResponseDto order) {
        PaymentNotificationDto paymentNotificationDto = new PaymentNotificationDto();
        paymentNotificationDto.setOrderId(order.getId());
        paymentNotificationDto.setUserId(order.getUserId());
        paymentNotificationDto.setDate(dto.getDate().toString());
        paymentNotificationDto.setCurrencyUnit(dto.getCurrencyType());
        paymentNotificationDto.setPayment(dto.getPayment());

        NotificationDto notificationDto = new NotificationDto();

        notificationDto.setEntity("TOUR_PAYMENT_SUCCEED");
        notificationDto.setAction(1);
        notificationDto.setData(paymentNotificationDto);

        return notificationDto;
    }
}
