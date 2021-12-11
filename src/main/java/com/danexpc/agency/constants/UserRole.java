package com.danexpc.agency.constants;

import lombok.Getter;

@Getter
public enum UserRole {
    CLIENT(1), MANAGER(2), ADMINISTRATOR(3);

    int id;

    UserRole(int id) {
        this.id = id;
    }
}
