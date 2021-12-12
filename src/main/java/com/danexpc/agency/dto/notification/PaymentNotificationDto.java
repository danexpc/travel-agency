package com.danexpc.agency.dto.notification;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;


@FieldDefaults(level = AccessLevel.PROTECTED)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentNotificationDto {

    Integer orderId;

    Integer userId;

    BigDecimal payment;

    String date;

    String currencyUnit;

}
