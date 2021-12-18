package com.danexpc.agency.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@Setter
@FieldDefaults(level= AccessLevel.PUBLIC)
public class ErrorResponseDto {
    String error;
}
