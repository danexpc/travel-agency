package com.danexpc.agency.service;

import com.danexpc.agency.dao.DaoSingletonFactory;
import com.danexpc.agency.dao.ScheduleDao;
import com.danexpc.agency.dao.ScheduleDao;
import com.danexpc.agency.dao.impl.DaoSingletonFactoryImpl;
import com.danexpc.agency.dto.request.ScheduleRequestDto;
import com.danexpc.agency.dto.response.ScheduleResponseDto;
import com.danexpc.agency.entity.ScheduleModel;
import com.danexpc.agency.helpers.Pagination;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ScheduleServiceTest {

    DaoSingletonFactory factory = mock(DaoSingletonFactory.class);
    ScheduleDao scheduleDao = mock(ScheduleDao.class);

    @Test
    void testGetSchedule() {
        Integer id = 1;
        Integer tourId = 2;
        Integer startLocationId = 3;
        Integer hotelId = 4;
        BigDecimal price = BigDecimal.valueOf(1000);
        LocalDateTime departureDate = LocalDateTime.now();
        Long duration = 1000L;
        Integer totalPlacesQty = 10;
        Integer remainingPlacesQty = 9;

        try (MockedStatic<DaoSingletonFactoryImpl> mocked = mockStatic(DaoSingletonFactoryImpl.class)) {
            mocked.when(DaoSingletonFactoryImpl::getInstance).thenReturn(factory);

            when(factory.getScheduleDao()).thenReturn(
                    scheduleDao
            );

            ScheduleService scheduleService = new ScheduleService();

            ScheduleModel model = new ScheduleModel(
                    id, tourId, startLocationId, hotelId, price, departureDate, duration,
                    true, totalPlacesQty, remainingPlacesQty
            );

            when(scheduleDao.findById(anyInt())).thenReturn(
                    model
            );

            ScheduleResponseDto expectedDto = ScheduleResponseDto.fromModel(model);


            ScheduleResponseDto actualDto = scheduleService.getScheduleById(id);

            assertEquals(expectedDto, actualDto);
        }
    }

    @Test
    void testGetSchedules() {
        List<ScheduleModel> expected = List.of(new ScheduleModel(), new ScheduleModel());

        try (MockedStatic<DaoSingletonFactoryImpl> mocked = mockStatic(DaoSingletonFactoryImpl.class)) {
            mocked.when(DaoSingletonFactoryImpl::getInstance).thenReturn(factory);

            when(factory.getScheduleDao()).thenReturn(
                    scheduleDao
            );

            ScheduleService scheduleService = new ScheduleService();

            when(scheduleDao.findAll(any(Pagination.class))).thenReturn(
                    expected
            );

            List<ScheduleResponseDto> actualDtos = scheduleService.getAllSchedules(new Pagination(10, 0));

            assertEquals(expected.size(), actualDtos.size());
        }
    }

    @Test
    void testCreateSchedule() {
        Integer tourId = 2;
        Integer startLocationId = 3;
        Integer hotelId = 4;
        BigDecimal price = BigDecimal.valueOf(1000);
        Timestamp departureDate = Timestamp.from(Instant.now());
        Long duration = 1000L;
        Integer totalPlacesQty = 10;
        Integer remainingPlacesQty = 9;

        ScheduleRequestDto request = new ScheduleRequestDto(tourId, startLocationId, hotelId, price, departureDate.getTime(),
                duration, true, totalPlacesQty, remainingPlacesQty);

        ScheduleModel expectedModel = request.buildModel();

        try (MockedStatic<DaoSingletonFactoryImpl> mocked = mockStatic(DaoSingletonFactoryImpl.class)) {
            mocked.when(DaoSingletonFactoryImpl::getInstance).thenReturn(factory);

            when(factory.getScheduleDao()).thenReturn(
                    scheduleDao
            );

            ScheduleService scheduleService = new ScheduleService();

            doNothing().when(scheduleDao).create(expectedModel);

            scheduleService.createSchedule(request);

            verify(scheduleDao, times(1)).create(expectedModel);
        }
    }

    @Test
    void testUpdateSchedule() {
        Integer id = 1;
        Integer tourId = 2;
        Integer startLocationId = 3;
        Integer hotelId = 4;
        BigDecimal price = BigDecimal.valueOf(1000);
        Timestamp departureDate = Timestamp.from(Instant.now());
        Long duration = 1000L;
        Integer totalPlacesQty = 10;
        Integer remainingPlacesQty = 9;

        ScheduleRequestDto request = new ScheduleRequestDto(tourId, startLocationId, hotelId, price, departureDate.getTime(),
                duration, true, totalPlacesQty, remainingPlacesQty);

        ScheduleModel expectedModel = request.buildModel();
        expectedModel.setId(id);

        try (MockedStatic<DaoSingletonFactoryImpl> mocked = mockStatic(DaoSingletonFactoryImpl.class)) {
            mocked.when(DaoSingletonFactoryImpl::getInstance).thenReturn(factory);

            when(factory.getScheduleDao()).thenReturn(
                    scheduleDao
            );

            ScheduleService scheduleService = new ScheduleService();

            doNothing().when(scheduleDao).update(expectedModel);

            scheduleService.updateSchedule(id, request);

            verify(scheduleDao, times(1)).update(expectedModel);
        }
    }

    @Test
    void testDeleteSchedule() {
        Integer id = 1;

        try (MockedStatic<DaoSingletonFactoryImpl> mocked = mockStatic(DaoSingletonFactoryImpl.class)) {
            mocked.when(DaoSingletonFactoryImpl::getInstance).thenReturn(factory);

            when(factory.getScheduleDao()).thenReturn(
                    scheduleDao
            );

            ScheduleService scheduleService = new ScheduleService();

            doNothing().when(scheduleDao).deleteById(id);

            scheduleService.deleteScheduleById(id);

            verify(scheduleDao, times(1)).deleteById(id);
        }
    }
}
