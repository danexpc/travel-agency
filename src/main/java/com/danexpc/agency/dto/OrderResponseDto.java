package com.danexpc.agency.dto;

import com.danexpc.agency.interfaces.Identifiable;
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
}
