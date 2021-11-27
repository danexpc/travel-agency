package com.danexpc.agency.dao.impl;

import com.danexpc.agency.dao.DaoSingletonFactory;
import com.danexpc.agency.dao.HotelDao;
import com.danexpc.agency.dao.LocationDao;
import com.danexpc.agency.dao.OrderDao;
import com.danexpc.agency.dao.TourDao;
import com.danexpc.agency.dao.UserDao;

public class DaoSingletonFactoryImpl implements DaoSingletonFactory {

    private static final DaoSingletonFactory factory = new DaoSingletonFactoryImpl();

    private final HotelDao hotelDao = new HotelDaoImpl();
    private final LocationDao locationDao = new LocationDaoImpl();
    private final OrderDao orderDao = new OrderDaoImpl();
    private final TourDao tourDao = new TourDaoImpl();
    private final UserDao userDao = new UserDaoImpl();

    public static DaoSingletonFactory getInstance() {
        return factory;
    }

    @Override
    public HotelDao getHotelDao() {
        return hotelDao;
    }

    @Override
    public LocationDao getLocationDao() {
        return locationDao;
    }

    @Override
    public OrderDao getOrderDao() {
        return orderDao;
    }

    @Override
    public TourDao getTourDao() {
        return tourDao;
    }

    @Override
    public UserDao getUserDao() {
        return userDao;
    }
}
