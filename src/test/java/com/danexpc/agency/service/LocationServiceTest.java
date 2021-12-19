package com.danexpc.agency.service;

import com.danexpc.agency.dao.DaoSingletonFactory;
import com.danexpc.agency.dao.LocationDao;
import com.danexpc.agency.dao.impl.DaoSingletonFactoryImpl;
import com.danexpc.agency.dto.request.LocationRequestDto;
import com.danexpc.agency.dto.response.LocationResponseDto;
import com.danexpc.agency.entity.LocationModel;
import com.danexpc.agency.helpers.Pagination;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.math.BigDecimal;
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

public class LocationServiceTest {

    DaoSingletonFactory factory = mock(DaoSingletonFactory.class);
    LocationDao locationDao = mock(LocationDao.class);

    @Test
    void testGetLocation() {
        Integer id = 1;
        String note = "testNote";
        String address = "testAddress";
        String street = "testStreet";
        String city = "testCity";
        String country = "testCountry";

        try (MockedStatic<DaoSingletonFactoryImpl> mocked = mockStatic(DaoSingletonFactoryImpl.class)) {
            mocked.when(DaoSingletonFactoryImpl::getInstance).thenReturn(factory);

            when(factory.getLocationDao()).thenReturn(
                    locationDao
            );

            LocationService locationService = new LocationService();

            LocationModel model = new LocationModel(
                    id, note, address, street, city, country
            );

            when(locationDao.findById(anyInt())).thenReturn(
                    model
            );

            LocationResponseDto expectedDto = LocationResponseDto.fromModel(model);

            LocationResponseDto actualDto = locationService.getLocationById(id);

            assertEquals(expectedDto, actualDto);
        }
    }

    @Test
    void testGetLocations() {
        List<LocationModel> expected = List.of(new LocationModel(), new LocationModel());

        try (MockedStatic<DaoSingletonFactoryImpl> mocked = mockStatic(DaoSingletonFactoryImpl.class)) {
            mocked.when(DaoSingletonFactoryImpl::getInstance).thenReturn(factory);

            when(factory.getLocationDao()).thenReturn(
                    locationDao
            );

            LocationService locationService = new LocationService();

            when(locationDao.findAll(any(Pagination.class))).thenReturn(
                    expected
            );

            List<LocationResponseDto> actualDtos = locationService.getAllLocations(new Pagination(10, 0));

            assertEquals(expected.size(), actualDtos.size());
        }
    }

    @Test
    void testCreateLocation() {
        String note = "testNote";
        String address = "testAddress";
        String street = "testStreet";
        String city = "testCity";
        String country = "testCountry";

        LocationRequestDto request = new LocationRequestDto(note, address, street, city, country);

        LocationModel expectedModel = request.buildModel();

        try (MockedStatic<DaoSingletonFactoryImpl> mocked = mockStatic(DaoSingletonFactoryImpl.class)) {
            mocked.when(DaoSingletonFactoryImpl::getInstance).thenReturn(factory);

            when(factory.getLocationDao()).thenReturn(
                    locationDao
            );

            LocationService locationService = new LocationService();

            doNothing().when(locationDao).create(expectedModel);

            locationService.createLocation(request);

            verify(locationDao, times(1)).create(expectedModel);
        }
    }

    @Test
    void testUpdateLocation() {
        Integer id = 1;
        String note = "testNote";
        String address = "testAddress";
        String street = "testStreet";
        String city = "testCity";
        String country = "testCountry";

        LocationRequestDto request = new LocationRequestDto(note, address, street, city, country);

        LocationModel expectedModel = request.buildModel();
        expectedModel.setId(id);

        try (MockedStatic<DaoSingletonFactoryImpl> mocked = mockStatic(DaoSingletonFactoryImpl.class)) {
            mocked.when(DaoSingletonFactoryImpl::getInstance).thenReturn(factory);

            when(factory.getLocationDao()).thenReturn(
                    locationDao
            );

            LocationService locationService = new LocationService();

            doNothing().when(locationDao).update(expectedModel);

            locationService.updateLocation(id, request);

            verify(locationDao, times(1)).update(expectedModel);
        }
    }

    @Test
    void testDeleteLocation() {
        Integer id = 1;

        try (MockedStatic<DaoSingletonFactoryImpl> mocked = mockStatic(DaoSingletonFactoryImpl.class)) {
            mocked.when(DaoSingletonFactoryImpl::getInstance).thenReturn(factory);

            when(factory.getLocationDao()).thenReturn(
                    locationDao
            );

            LocationService locationService = new LocationService();

            doNothing().when(locationDao).deleteById(id);

            locationService.deleteLocationById(id);

            verify(locationDao, times(1)).deleteById(id);
        }
    }
}
