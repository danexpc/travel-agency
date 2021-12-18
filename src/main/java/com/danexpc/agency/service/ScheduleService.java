package com.danexpc.agency.service;

import com.danexpc.agency.dao.DaoSingletonFactory;
import com.danexpc.agency.dao.ScheduleDao;
import com.danexpc.agency.dao.impl.DaoSingletonFactoryImpl;
import com.danexpc.agency.dto.request.ScheduleRequestDto;
import com.danexpc.agency.dto.response.ScheduleResponseDto;
import com.danexpc.agency.entity.ScheduleModel;
import com.danexpc.agency.helpers.Pagination;
import lombok.SneakyThrows;

import java.util.List;
import java.util.stream.Collectors;

public class ScheduleService {

    private final DaoSingletonFactory factory = DaoSingletonFactoryImpl.getInstance();

    private final ScheduleDao scheduleDao = factory.getScheduleDao();


    public void createSchedule(ScheduleRequestDto dto) {
        ScheduleModel model = dto.buildModel();

        scheduleDao.create(model);
    }

    public void updateSchedule(Integer id, ScheduleRequestDto dto) {
        ScheduleModel model  = dto.buildModel();
        model.setId(id);

        scheduleDao.update(model);
    }

    @SneakyThrows
    public ScheduleResponseDto getScheduleById(Integer id) {
        ScheduleModel model = scheduleDao.findById(id);

        return ScheduleResponseDto.fromModel(model);
    }

    @SneakyThrows
    public List<ScheduleResponseDto> getAllSchedules(Pagination pagination) {
        List<ScheduleModel> models = scheduleDao.findAll(pagination);

        return models.stream().parallel().map(ScheduleResponseDto::fromModel).collect(Collectors.toUnmodifiableList());
    }

    public void deleteScheduleById(Integer id) {
        scheduleDao.deleteById(id);
    }
}
