package com.danexpc.agency.service;

import com.danexpc.agency.dao.DaoSingletonFactory;
import com.danexpc.agency.dao.HotelDao;
import com.danexpc.agency.dao.impl.DaoSingletonFactoryImpl;
import com.danexpc.agency.dto.HotelRequestDto;
import com.danexpc.agency.dto.HotelResponseDto;

import java.util.List;

public class HotelService {

    private final DaoSingletonFactory factory = DaoSingletonFactoryImpl.getInstance();

    private final HotelDao hotelDao = factory.getHotelDao();

    public void createHotel(HotelRequestDto dto) {

    }

    public void updateHotel(HotelRequestDto dto) {

    }

    public HotelResponseDto getHotelById(Integer id) {
        return null;
    }

    public List<HotelResponseDto> getAllHotels() {
        return null;
    }

    public void deleteHotelById(Integer id) {
    }

}
