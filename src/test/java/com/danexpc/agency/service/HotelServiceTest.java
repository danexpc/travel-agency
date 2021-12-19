package com.danexpc.agency.service;

import com.danexpc.agency.dao.DaoSingletonFactory;
import com.danexpc.agency.dao.HotelDao;
import com.danexpc.agency.dao.impl.DaoSingletonFactoryImpl;
import com.danexpc.agency.dto.request.HotelRequestDto;
import com.danexpc.agency.dto.response.HotelResponseDto;
import com.danexpc.agency.entity.HotelModel;
import com.danexpc.agency.helpers.Pagination;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

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

public class HotelServiceTest {

    DaoSingletonFactory factory = mock(DaoSingletonFactory.class);
    HotelDao hotelDao = mock(HotelDao.class);

    @Test
    void testGetHotel() {
        Integer id = 1;
        Integer locationId = 2;
        String name = "testName";
        String description = "testDescription";
        Integer type = 1;

        try (MockedStatic<DaoSingletonFactoryImpl> mocked = mockStatic(DaoSingletonFactoryImpl.class)) {
            mocked.when(DaoSingletonFactoryImpl::getInstance).thenReturn(factory);

            when(factory.getHotelDao()).thenReturn(
                    hotelDao
            );

            HotelService hotelService = new HotelService();

            HotelModel model = new HotelModel(
                    id, locationId, name, description, type
            );

            when(hotelDao.findById(anyInt())).thenReturn(
                    model
            );

            HotelResponseDto expectedDto = HotelResponseDto.fromModel(model);


            HotelResponseDto actualDto = hotelService.getHotelById(id);

            assertEquals(expectedDto, actualDto);
        }
    }

    @Test
    void testGetHotels() {
        List<HotelModel> expected = List.of(new HotelModel(), new HotelModel());

        try (MockedStatic<DaoSingletonFactoryImpl> mocked = mockStatic(DaoSingletonFactoryImpl.class)) {
            mocked.when(DaoSingletonFactoryImpl::getInstance).thenReturn(factory);

            when(factory.getHotelDao()).thenReturn(
                    hotelDao
            );

            HotelService hotelService = new HotelService();

            when(hotelDao.findAll(any(Pagination.class))).thenReturn(
                    expected
            );

            List<HotelResponseDto> actualDtos = hotelService.getAllHotels(new Pagination(10, 0));

            assertEquals(expected.size(), actualDtos.size());
        }
    }

    @Test
    void testCreateHotel() {
        Integer locationId = 2;
        String name = "testName";
        String description = "testDescription";
        Integer type = 1;

        HotelRequestDto request = new HotelRequestDto(locationId, name, description, type);

        HotelModel expectedModel = request.buildModel();

        try (MockedStatic<DaoSingletonFactoryImpl> mocked = mockStatic(DaoSingletonFactoryImpl.class)) {
            mocked.when(DaoSingletonFactoryImpl::getInstance).thenReturn(factory);

            when(factory.getHotelDao()).thenReturn(
                    hotelDao
            );

            HotelService hotelService = new HotelService();

            doNothing().when(hotelDao).create(expectedModel);

            hotelService.createHotel(request);

            verify(hotelDao, times(1)).create(expectedModel);
        }
    }

    @Test
    void testUpdateHotel() {
        Integer id = 1;
        Integer locationId = 2;
        String name = "testName";
        String description = "testDescription";
        Integer type = 1;

        HotelRequestDto request = new HotelRequestDto(locationId, name, description, type);

        HotelModel expectedModel = request.buildModel();
        expectedModel.setId(id);

        try (MockedStatic<DaoSingletonFactoryImpl> mocked = mockStatic(DaoSingletonFactoryImpl.class)) {
            mocked.when(DaoSingletonFactoryImpl::getInstance).thenReturn(factory);

            when(factory.getHotelDao()).thenReturn(
                    hotelDao
            );

            HotelService hotelService = new HotelService();

            doNothing().when(hotelDao).update(expectedModel);

            hotelService.updateHotel(id, request);

            verify(hotelDao, times(1)).update(expectedModel);
        }
    }

    @Test
    void testDeleteHotel() {
        Integer id = 1;

        try (MockedStatic<DaoSingletonFactoryImpl> mocked = mockStatic(DaoSingletonFactoryImpl.class)) {
            mocked.when(DaoSingletonFactoryImpl::getInstance).thenReturn(factory);

            when(factory.getHotelDao()).thenReturn(
                    hotelDao
            );

            HotelService hotelService = new HotelService();

            doNothing().when(hotelDao).deleteById(id);

            hotelService.deleteHotelById(id);

            verify(hotelDao, times(1)).deleteById(id);
        }
    }
}
