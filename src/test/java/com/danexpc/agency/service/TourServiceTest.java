package com.danexpc.agency.service;

import com.danexpc.agency.dao.DaoSingletonFactory;
import com.danexpc.agency.dao.TourDao;
import com.danexpc.agency.dao.impl.DaoSingletonFactoryImpl;
import com.danexpc.agency.dto.request.TourRequestDto;
import com.danexpc.agency.dto.response.TourResponseDto;
import com.danexpc.agency.entity.TourModel;
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

public class TourServiceTest {

    DaoSingletonFactory factory = mock(DaoSingletonFactory.class);
    TourDao tourDao = mock(TourDao.class);

    @Test
    void testGetTour() {
       Integer id = 1;
        String name = "testName";
        String description = "testDescription";
        Integer type = 2;

        try (MockedStatic<DaoSingletonFactoryImpl> mocked = mockStatic(DaoSingletonFactoryImpl.class)) {
            mocked.when(DaoSingletonFactoryImpl::getInstance).thenReturn(factory);

            when(factory.getTourDao()).thenReturn(
                    tourDao
            );

            TourService tourService = new TourService();

            TourModel model = new TourModel(
                    id, name, description, type
            );

            when(tourDao.findById(anyInt())).thenReturn(
                    model
            );

            TourResponseDto expectedDto = TourResponseDto.fromModel(model);


            TourResponseDto actualDto = tourService.getTourById(id);

            assertEquals(expectedDto, actualDto);
        }
    }

    @Test
    void testGetTours() {
        List<TourModel> expected = List.of(new TourModel(), new TourModel());

        try (MockedStatic<DaoSingletonFactoryImpl> mocked = mockStatic(DaoSingletonFactoryImpl.class)) {
            mocked.when(DaoSingletonFactoryImpl::getInstance).thenReturn(factory);

            when(factory.getTourDao()).thenReturn(
                    tourDao
            );

            TourService tourService = new TourService();

            when(tourDao.findAll(any(Pagination.class))).thenReturn(
                    expected
            );

            List<TourResponseDto> actualDtos = tourService.getAllTours(new Pagination(10, 0));

            assertEquals(expected.size(), actualDtos.size());
        }
    }

    @Test
    void testCreateTour() {
        String name = "testName";
        String description = "testDescription";
        Integer type = 2;

        TourRequestDto request = new TourRequestDto(name, description, type);

        TourModel expectedModel = request.buildModel();

        try (MockedStatic<DaoSingletonFactoryImpl> mocked = mockStatic(DaoSingletonFactoryImpl.class)) {
            mocked.when(DaoSingletonFactoryImpl::getInstance).thenReturn(factory);

            when(factory.getTourDao()).thenReturn(
                    tourDao
            );

            TourService tourService = new TourService();

            doNothing().when(tourDao).create(expectedModel);

            tourService.createTour(request);

            verify(tourDao, times(1)).create(expectedModel);
        }
    }

    @Test
    void testUpdateTour() {
        Integer id = 1;
        String name = "testName";
        String description = "testDescription";
        Integer type = 2;

        TourRequestDto request = new TourRequestDto(name, description, type);

        TourModel expectedModel = request.buildModel();
        expectedModel.setId(id);

        try (MockedStatic<DaoSingletonFactoryImpl> mocked = mockStatic(DaoSingletonFactoryImpl.class)) {
            mocked.when(DaoSingletonFactoryImpl::getInstance).thenReturn(factory);

            when(factory.getTourDao()).thenReturn(
                    tourDao
            );

            TourService tourService = new TourService();

            doNothing().when(tourDao).update(expectedModel);

            tourService.updateTour(id, request);

            verify(tourDao, times(1)).update(expectedModel);
        }
    }

    @Test
    void testDeleteTour() {
        Integer id = 1;

        try (MockedStatic<DaoSingletonFactoryImpl> mocked = mockStatic(DaoSingletonFactoryImpl.class)) {
            mocked.when(DaoSingletonFactoryImpl::getInstance).thenReturn(factory);

            when(factory.getTourDao()).thenReturn(
                    tourDao
            );

            TourService tourService = new TourService();

            doNothing().when(tourDao).deleteById(id);

            tourService.deleteTourById(id);

            verify(tourDao, times(1)).deleteById(id);
        }
    }
}
