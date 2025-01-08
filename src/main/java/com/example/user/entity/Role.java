package com.example.user.entity;

public enum Role {
    CUSTOMER, OWNER;

    public String getAuthority() {
        return "ROLE_" + this.name();
    }
}
