package com.danexpc.agency.entity;

import com.danexpc.agency.entity.interfaces.Identifiable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentModel implements Identifiable<Integer> {

    Integer id;

    Integer orderId;

    BigDecimal payment;

    LocalDateTime date;

    String currencyType;

    Double exchangeRate;
}
