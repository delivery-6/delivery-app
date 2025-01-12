package com.example.user.entity;

import java.util.Arrays;

public enum Role {
    NONE, CUSTOMER, OWNER;

    public String getAuthority() {
        return "ROLE_" + this.name();
    }

    public static boolean isValid(String value) {
        return Arrays.stream(Role.values())
                .anyMatch(role -> role.name().equalsIgnoreCase(value));
    }
}
