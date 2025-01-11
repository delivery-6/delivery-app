package com.example.exception.custom;

import com.example.exception.CustomException;

public class BadRequestException extends CustomException {
    public BadRequestException(String message) {
        super(message, 400);
    }
}