package com.danexpc.agency.constants;

public enum OrderStatus {
    REGISTERED(1), PAID(2), CANCELED(3);

    int id;

    OrderStatus(int id) {
        this.id = id;
    }
}
