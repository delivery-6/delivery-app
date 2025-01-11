package com.example.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private final ErrorCode errorCode;
    private final String customMessage;

    private CustomException(ErrorCode errorCode, String customMessage) {
        super(customMessage != null ? customMessage : errorCode.getDefaultMessage());
        this.errorCode = errorCode;
        this.customMessage = customMessage;
    }


    // 기본 메시지 사용
    //throw CustomException.of(ErrorCode.NOT_FOUND);
    public static CustomException of(ErrorCode errorCode) {
        return new CustomException(errorCode, null);
    }

    // 사용자 정의 메시지 제공
    //throw CustomException.of(ErrorCode.BAD_REQUEST, "커스텀 메세지를 추가하면 됩니다. eg) 필수 파라미터가 누락되었습니다.");
    public static CustomException of(ErrorCode errorCode, String customMessage) {
        return new CustomException(errorCode, customMessage);
    }





}
