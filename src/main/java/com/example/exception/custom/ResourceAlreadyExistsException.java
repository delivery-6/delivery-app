package com.example.exception.custom;

import com.example.exception.CustomException;

public class ResourceAlreadyExistsException extends CustomException {
    public ResourceAlreadyExistsException(String message) {
        super(message, 409); // HTTP 409: Conflict
    }
}
