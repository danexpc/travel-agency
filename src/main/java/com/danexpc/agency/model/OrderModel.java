package com.danexpc.agency.model;

import com.danexpc.agency.interfaces.Identifiable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderModel implements Identifiable<Integer> {

    private Integer id;

    private Integer userId;

    private Integer scheduleId;

    private Integer orderStatus;

    private Float discount;

    private BigDecimal finalPrice;
}
