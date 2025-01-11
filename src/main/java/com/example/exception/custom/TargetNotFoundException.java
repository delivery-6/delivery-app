package com.example.exception.custom;

import com.example.exception.CustomException;

public class TargetNotFoundException extends CustomException {

    public TargetNotFoundException(String message) {
        super(message, 404);
    }
}
