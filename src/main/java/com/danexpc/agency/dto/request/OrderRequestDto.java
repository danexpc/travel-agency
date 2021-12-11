package com.danexpc.agency.dto.request;

import com.danexpc.agency.entity.OrderModel;
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
public class OrderRequestDto {

    Integer userId;

    Integer scheduleId;

    Integer orderStatus;

    Float discount;

    BigDecimal finalPrice;

    public OrderModel buildModel() {
        OrderModel model = new OrderModel();
        model.setUserId(userId);
        model.setScheduleId(scheduleId);
        model.setOrderStatus(orderStatus);
        model.setDiscount(discount);
        model.setFinalPrice(finalPrice);

        return model;
    }
}
