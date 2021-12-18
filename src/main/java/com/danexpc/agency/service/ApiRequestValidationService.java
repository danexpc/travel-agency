package com.danexpc.agency.service;

import com.danexpc.agency.dto.request.HotelRequestDto;
import com.danexpc.agency.dto.request.LocationRequestDto;
import com.danexpc.agency.dto.request.OrderRequestDto;
import com.danexpc.agency.dto.request.PaymentRequestDto;
import com.danexpc.agency.dto.request.ScheduleRequestDto;
import com.danexpc.agency.dto.request.TourRequestDto;
import com.danexpc.agency.dto.request.UserAuthRequest;
import com.danexpc.agency.dto.request.UserRequestDto;
import com.danexpc.agency.exceptions.InvalidRequestDtoException;

public class ApiRequestValidationService {

    public void validateHotelRequestDto(HotelRequestDto dto) {
        if (dto.getLocationId() == null ||
                dto.getType() == null ||
                dto.getDescription() == null ||
                dto.getName() == null
        ) {
            throw new InvalidRequestDtoException("Hotel request is invalid");
        }
    }

    public void validateLocationRequestDto(LocationRequestDto dto) {
        if (dto.getNote() == null ||
                dto.getAddress() == null ||
                dto.getCity() == null ||
                dto.getCountry() == null ||
                dto.getStreet() == null
        ) {
            throw new InvalidRequestDtoException("Location request is invalid");
        }
    }

    public void validateOrderRequestDto(OrderRequestDto dto) {
        if (dto.getDiscount() == null ||
                dto.getOrderStatus() == null ||
                dto.getFinalPrice() == null ||
                dto.getScheduleId() == null ||
                dto.getUserId() == null
        ) {
            throw new InvalidRequestDtoException("Order request is invalid");
        }
    }

    public void validatePaymentRequestDto(PaymentRequestDto dto) {
        if (dto.getPayment() == null ||
                dto.getDate() == null ||
                dto.getCurrencyType() == null ||
                dto.getOrderId() == null ||
                dto.getExchangeRate() == null
        ) {
            throw new InvalidRequestDtoException("Payment request is invalid");
        }
    }

    public void validateScheduleRequestDto(ScheduleRequestDto dto) {
        if (dto.getDuration() == null ||
                dto.getIsOnFire() == null ||
                dto.getDepartureDate() == null ||
                dto.getPrice() == null ||
                dto.getHotelId() == null ||
                dto.getRemainingPlacesQty() == null ||
                dto.getStartLocationId() == null ||
                dto.getTotalPlacesQty() == null ||
                dto.getTourId() == null
        ) {
            throw new InvalidRequestDtoException("Schedule request is invalid");
        }
    }

    public void validateTourRequestDto(TourRequestDto dto) {
        if (dto.getDescription() == null ||
                dto.getType() == null ||
                dto.getName() == null
        ) {
            throw new InvalidRequestDtoException("Tour request is invalid");
        }
    }

    public void validateAuthRequestDto(UserAuthRequest dto) {
        if (dto.getEmail() == null ||
                dto.getPassword() == null
        ) {
            throw new InvalidRequestDtoException("Auth request is invalid");
        }
    }

    public void validateUserRequestDto(UserRequestDto dto) {
        if (dto.getPassword() == null ||
                dto.getEmail() == null ||
                dto.getType() == null
        ) {
            throw new InvalidRequestDtoException("User request is invalid");
        }
    }
}
