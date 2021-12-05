package com.danexpc.agency.service;

import com.danexpc.agency.dao.DaoSingletonFactory;
import com.danexpc.agency.dao.HotelDao;
import com.danexpc.agency.dao.impl.DaoSingletonFactoryImpl;
import com.danexpc.agency.dto.request.HotelRequestDto;
import com.danexpc.agency.dto.response.HotelResponseDto;
import com.danexpc.agency.model.HotelModel;
import lombok.SneakyThrows;

import java.util.List;
import java.util.stream.Collectors;

public class HotelService {

    private final DaoSingletonFactory factory = DaoSingletonFactoryImpl.getInstance();

    private final HotelDao hotelDao = factory.getHotelDao();

    public void createHotel(HotelRequestDto dto) {
        HotelModel model = dto.buildModel();

        hotelDao.create(model);
    }

    public void updateHotel(Integer id, HotelRequestDto dto) {
        HotelModel model = dto.buildModel();
        model.setId(id);

        hotelDao.update(model);
    }

    @SneakyThrows
    public HotelResponseDto getHotelById(Integer id) {
        HotelModel model = hotelDao.findById(id);

        return  HotelResponseDto.fromModel(model);
    }

    @SneakyThrows
    public List<HotelResponseDto> getAllHotels() {
        List<HotelModel> models = hotelDao.findAll();

        return models.stream().parallel().map(HotelResponseDto::fromModel).collect(Collectors.toUnmodifiableList());
    }

    public void deleteHotelById(Integer id) {
        hotelDao.deleteById(id);
    }

}
