package com.danexpc.agency.constants;

public enum HotelType {
    BUSINESS(1), AIRPORT(2), SUITES(3), RESIDENTIAL(4), RESORT(5), TIMESHARE(6), CASINO(7), CONVENTION(8), CONFERENCE(9);

    int id;

    HotelType(int id) {
        this.id = id;
    }
}
