package com.danexpc.agency.dto.response;

import com.danexpc.agency.dto.request.OrderRequestDto;
import com.danexpc.agency.interfaces.Identifiable;
import com.danexpc.agency.model.OrderModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDto extends OrderRequestDto implements Identifiable<Integer> {

    Integer id;

    public static OrderResponseDto fromModel(OrderModel model) {
        OrderResponseDto dto = new OrderResponseDto();
        dto.setId(model.getId());
        dto.setUserId(model.getUserId());
        dto.setScheduleId(model.getScheduleId());
        dto.setOrderStatus(model.getOrderStatus());
        dto.setDiscount(model.getDiscount());
        dto.setFinalPrice(model.getFinalPrice());

        return dto;
    }
}
