package com.example.user.entity;

public enum Role {
    NONE, CUSTOMER, OWNER;

    public String getAuthority() {
        return "ROLE_" + this.name();
    }
}
