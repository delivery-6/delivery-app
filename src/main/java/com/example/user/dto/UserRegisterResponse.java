package com.example.user.dto;

public class UserRegisterResponse {
    private String email;
    private String message;

    public UserRegisterResponse(String email, String message) {
        this.email = email;
        this.message = message;
    }

    // Getters
    public String getEmail() {
        return email;
    }

    public String getMessage() {
        return message;
    }
}

