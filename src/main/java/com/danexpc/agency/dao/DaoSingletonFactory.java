package com.danexpc.agency.dao;

public interface DaoSingletonFactory {

    HotelDao getHotelDao();

    LocationDao getLocationDao();

    OrderDao getOrderDao();

    TourDao getTourDao();

    UserDao getUserDao();

    ScheduleDao getScheduleDao();

    PaymentDao getPaymentDao();
}
