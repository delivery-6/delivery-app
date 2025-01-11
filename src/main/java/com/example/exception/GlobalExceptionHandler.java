package com.example.exception;

import com.example.exception.custom.BadRequestException;
import com.example.exception.custom.PermissionDeniedException;
import com.example.exception.custom.ResourceAlreadyExistsException;
import com.example.exception.custom.TargetNotFoundException;
import org.aspectj.apache.bcel.generic.TargetLostException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<String> handleCustomException(CustomException ex) {
        return ResponseEntity
                .status(ex.getErrorCode())
                .body("오류 발생: " + ex.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleBadRequestException(BadRequestException ex){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("잘못된 요청: " + ex.getMessage());
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<String> handleResourceAlreadyExistsException(ResourceAlreadyExistsException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body("리소스 충돌: " + ex.getMessage());
    }

    @ExceptionHandler(PermissionDeniedException.class)
    public ResponseEntity<String> handlePermissionDeniedException(PermissionDeniedException ex) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body("권한 없음: " + ex.getMessage());
    }

    @ExceptionHandler(TargetLostException.class)
    public ResponseEntity<String> handleTargetNotFoundException(TargetNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("대상을 찾을 수 없습니다:" + ex.getMessage());
    }


}
