package com.danexpc.agency.dto.request;

import com.danexpc.agency.entity.PaymentModel;
import com.danexpc.agency.helpers.DateTimeConverter;
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
public class PaymentRequestDto {

    Integer orderId;

    BigDecimal payment;

    Long date;

    String currencyType;

    Double exchangeRate;

    public PaymentModel buildModel() {
        PaymentModel model = new PaymentModel();
        model.setOrderId(orderId);
        model.setPayment(payment);
        model.setDate(DateTimeConverter.fromEpoch(date));
        model.setCurrencyType(currencyType);
        model.setCurrencyType(currencyType);
        model.setExchangeRate(exchangeRate);

        return model;
    }
}
