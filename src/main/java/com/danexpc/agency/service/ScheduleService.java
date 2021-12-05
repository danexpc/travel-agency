package com.danexpc.agency.service;

import com.danexpc.agency.dao.DaoSingletonFactory;
import com.danexpc.agency.dao.ScheduleDao;
import com.danexpc.agency.dao.impl.DaoSingletonFactoryImpl;
import com.danexpc.agency.dto.request.ScheduleRequestDto;
import com.danexpc.agency.dto.response.ScheduleResponseDto;

import java.util.List;

public class ScheduleService {

    private final DaoSingletonFactory factory = DaoSingletonFactoryImpl.getInstance();

    private final ScheduleDao scheduleDao = factory.getScheduleDao();


    public void createSchedule(ScheduleRequestDto dto) {

    }

    public void updateSchedule(Integer id, ScheduleRequestDto dto) {

    }

    public ScheduleResponseDto getScheduleById(Integer id) {
        return null;
    }

    public List<ScheduleResponseDto> getAllSchedules() {
        return null;
    }

    public void deleteScheduleById(Integer id) {
    }
}
