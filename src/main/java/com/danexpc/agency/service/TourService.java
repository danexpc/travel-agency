package com.danexpc.agency.service;

import com.danexpc.agency.dao.DaoSingletonFactory;
import com.danexpc.agency.dao.TourDao;
import com.danexpc.agency.dao.impl.DaoSingletonFactoryImpl;
import com.danexpc.agency.dto.TourRequestDto;
import com.danexpc.agency.dto.TourResponseDto;

import java.util.List;

public class TourService {

    private final DaoSingletonFactory factory = DaoSingletonFactoryImpl.getInstance();

    private final TourDao tourDao = factory.getTourDao();


    public void createTour(TourRequestDto dto) {

    }

    public void updateTour(TourRequestDto dto) {

    }

    public TourResponseDto getTourById(Integer id) {
        return null;
    }

    public List<TourResponseDto> getAllTours() {
        return null;
    }

    public void deleteTourById(Integer id) {
    }
}
