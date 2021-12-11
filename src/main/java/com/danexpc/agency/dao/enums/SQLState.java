package com.danexpc.agency.dao.enums;

import lombok.Getter;

@Getter
public enum SQLState {
    UNIQUE_VIOLATION("23505");

    String state;

    SQLState(String s) {
        this.state = s;
    }
}
