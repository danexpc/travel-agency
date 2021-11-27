package com.danexpc.agency.dto;

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

    private Integer userId;

    private Integer tourId;

    private Integer orderStatus;

    private Float discount;

    private BigDecimal finalPrice;
}
