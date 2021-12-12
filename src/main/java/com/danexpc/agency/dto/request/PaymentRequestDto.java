package com.danexpc.agency.dto.request;

import com.danexpc.agency.entity.PaymentModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@FieldDefaults(level = AccessLevel.PROTECTED)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequestDto {

    Integer orderId;

    BigDecimal payment;

    LocalDateTime date;

    String currencyType;

    Double exchangeRate;

    public PaymentModel buildModel() {
        PaymentModel model = new PaymentModel();
        model.setOrderId(orderId);
        model.setPayment(payment);
        model.setDate(date);
        model.setCurrencyType(currencyType);
        model.setCurrencyType(currencyType);
        model.setExchangeRate(exchangeRate);

        return model;
    }
}
