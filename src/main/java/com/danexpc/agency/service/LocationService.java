package com.danexpc.agency.service;

import com.danexpc.agency.dao.DaoSingletonFactory;
import com.danexpc.agency.dao.LocationDao;
import com.danexpc.agency.dao.impl.DaoSingletonFactoryImpl;
import com.danexpc.agency.dto.request.LocationRequestDto;
import com.danexpc.agency.dto.response.LocationResponseDto;
import com.danexpc.agency.model.LocationModel;
import lombok.SneakyThrows;

import java.util.List;
import java.util.stream.Collectors;

public class LocationService {

    private final DaoSingletonFactory factory = DaoSingletonFactoryImpl.getInstance();

    private final LocationDao locationDao = factory.getLocationDao();

    public void createLocation(LocationRequestDto dto) {
        LocationModel model = dto.buildModel();

        locationDao.create(model);
    }

    public void updateLocation(Integer id, LocationRequestDto dto) {
        LocationModel model = dto.buildModel();
        model.setId(id);

        locationDao.update(model);
    }

    @SneakyThrows
    public LocationResponseDto getLocationById(Integer id) {
        LocationModel model = locationDao.findById(id);

        return LocationResponseDto.fromModel(model);
    }

    @SneakyThrows
    public List<LocationResponseDto> getAllLocations() {
        List<LocationModel> models = locationDao.findAll();

        return models.stream().parallel().map(LocationResponseDto::fromModel).collect(Collectors.toUnmodifiableList());
    }

    public void deleteLocationById(Integer id) {
        locationDao.deleteById(id);
    }
}
