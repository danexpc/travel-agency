package com.danexpc.agency.dto.response;

import com.danexpc.agency.dto.request.PaymentRequestDto;
import com.danexpc.agency.entity.PaymentModel;
import com.danexpc.agency.helpers.DateTimeConverter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.ZoneId;

@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponseDto extends PaymentRequestDto {

    Integer id;

    public static PaymentResponseDto fromModel(PaymentModel model) {
        PaymentResponseDto dto = new PaymentResponseDto();
        dto.setId(model.getId());
        dto.setOrderId(model.getOrderId());
        dto.setPayment(model.getPayment());
        dto.setDate(DateTimeConverter.dateTimeToEpoch(model.getDate()));
        dto.setCurrencyType(model.getCurrencyType());
        dto.setExchangeRate(model.getExchangeRate());

        return dto;
    }
}
