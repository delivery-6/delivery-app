package com.example.exception.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
@Builder
public class ErrorResponseDto {
    private final int status;
    private final String message;

    public static ErrorResponseDto of(HttpStatus status, String message) {
        return ErrorResponseDto.builder()
                .status(status.value())
                .message(message)
                .build();
    }
}
