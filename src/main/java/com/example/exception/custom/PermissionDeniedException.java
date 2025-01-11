package com.example.exception.custom;

import com.example.exception.CustomException;

public class PermissionDeniedException extends CustomException {
    public PermissionDeniedException(String message) {
        super(message, 403); // HTTP 403: Forbidden
    }
}
