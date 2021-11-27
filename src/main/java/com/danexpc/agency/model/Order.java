package com.danexpc.agency.model;

import com.danexpc.agency.interfaces.Identifiable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Identifiable<Integer> {

    private Integer id;

    private Integer userId;

    private Integer tourId;

    private Integer orderStatus;

    private Double discount;

    private BigDecimal finalPrice;
}
