package com.danexpc.agency.service;

import com.danexpc.agency.dao.DaoSingletonFactory;
import com.danexpc.agency.dao.TourDao;
import com.danexpc.agency.dao.impl.DaoSingletonFactoryImpl;
import com.danexpc.agency.dto.request.TourRequestDto;
import com.danexpc.agency.dto.response.TourResponseDto;
import com.danexpc.agency.entity.TourModel;
import com.danexpc.agency.helpers.Pagination;
import lombok.SneakyThrows;

import java.util.List;
import java.util.stream.Collectors;

public class TourService {

    private final DaoSingletonFactory factory = DaoSingletonFactoryImpl.getInstance();

    private final TourDao tourDao = factory.getTourDao();


    public void createTour(TourRequestDto dto) {
        TourModel model = dto.buildModel();

        tourDao.create(model);
    }

    public void updateTour(Integer id, TourRequestDto dto) {
        TourModel model = dto.buildModel();
        model.setId(id);
        tourDao.update(model);
    }

    @SneakyThrows
    public TourResponseDto getTourById(Integer id) {
        TourModel model = tourDao.findById(id);
        return TourResponseDto.fromModel(model);
    }

    @SneakyThrows
    public List<TourResponseDto> getAllTours(Pagination pagination) {
        List<TourModel> models = tourDao.findAll(pagination);

        return models.stream().parallel().map(TourResponseDto::fromModel).collect(Collectors.toUnmodifiableList());
    }

    public void deleteTourById(Integer id) {
        tourDao.deleteById(id);
    }
}
