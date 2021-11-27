package com.danexpc.agency.service;

import com.danexpc.agency.dao.DaoSingletonFactory;
import com.danexpc.agency.dao.LocationDao;
import com.danexpc.agency.dao.impl.DaoSingletonFactoryImpl;
import com.danexpc.agency.dto.LocationRequestDto;
import com.danexpc.agency.dto.LocationResponseDto;

import java.util.List;

public class LocationService {

    private final DaoSingletonFactory factory = DaoSingletonFactoryImpl.getInstance();

    private final LocationDao locationDao = factory.getLocationDao();

    public void createLocation(LocationRequestDto dto) {

    }

    public void updateLocation(LocationRequestDto dto) {

    }

    public LocationResponseDto getLocationById(Integer id) {
        return null;
    }

    public List<LocationResponseDto> getAllLocations() {
        return null;
    }

    public void deleteLocationById(Integer id) {
    }
}
