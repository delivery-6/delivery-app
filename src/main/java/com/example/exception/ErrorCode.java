package com.example.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // 400 Bad Request - 클라이언트가 잘못된 요청을 보냈습니다.
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),

    // 401 Unauthorized - 인증되지 않은 사용자가 요청을 시도했습니다.
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "인증되지 않은 접근입니다."),

    // 402 Payment Required - 결제가 필요합니다 (일반적으로 사용되지 않음).
    PAYMENT_REQUIRED(HttpStatus.PAYMENT_REQUIRED, "결제가 필요합니다."),

    // 403 Forbidden - 권한이 없어 요청이 거부되었습니다.
    FORBIDDEN(HttpStatus.FORBIDDEN, "접근이 금지되었습니다."),

    // 404 Not Found - 요청한 리소스를 찾을 수 없습니다.
    NOT_FOUND(HttpStatus.NOT_FOUND, "리소스를 찾을 수 없습니다."),

    // 405 Method Not Allowed - 허용되지 않은 HTTP 메서드로 요청했습니다.
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "허용되지 않은 HTTP 메서드입니다."),

    // 406 Not Acceptable - 클라이언트가 요청한 내용이 허용되지 않습니다.
    NOT_ACCEPTABLE(HttpStatus.NOT_ACCEPTABLE, "허용되지 않는 요청입니다."),

    // 407 Proxy Authentication Required - 프록시 인증이 필요합니다.
    PROXY_AUTHENTICATION_REQUIRED(HttpStatus.PROXY_AUTHENTICATION_REQUIRED, "프록시 인증이 필요합니다."),

    // 408 Request Timeout - 요청 시간이 초과되었습니다.
    REQUEST_TIMEOUT(HttpStatus.REQUEST_TIMEOUT, "요청 시간이 초과되었습니다."),

    // 409 Conflict - 요청이 서버 상태와 충돌했습니다.
    CONFLICT(HttpStatus.CONFLICT, "충돌이 발생했습니다."),

    // 410 Gone - 요청한 리소스가 더 이상 사용되지 않습니다.
    GONE(HttpStatus.GONE, "요청한 리소스가 더 이상 사용되지 않습니다."),

    // 411 Length Required - Content-Length 헤더가 필요합니다.
    LENGTH_REQUIRED(HttpStatus.LENGTH_REQUIRED, "길이 헤더가 필요합니다."),

    // 412 Precondition Failed - 요청의 전제 조건이 실패했습니다.
    PRECONDITION_FAILED(HttpStatus.PRECONDITION_FAILED, "전제 조건이 실패했습니다."),

    // 413 Payload Too Large - 요청의 페이로드가 너무 큽니다.
    PAYLOAD_TOO_LARGE(HttpStatus.PAYLOAD_TOO_LARGE, "페이로드가 너무 큽니다."),

    // 414 URI Too Long - 요청 URI가 너무 깁니다.
    URI_TOO_LONG(HttpStatus.URI_TOO_LONG, "URI가 너무 깁니다."),

    // 415 Unsupported Media Type - 지원되지 않는 미디어 타입입니다.
    UNSUPPORTED_MEDIA_TYPE(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "지원되지 않는 미디어 타입입니다."),

    // 417 Expectation Failed - 요청이 기대를 충족하지 못했습니다.
    EXPECTATION_FAILED(HttpStatus.EXPECTATION_FAILED, "요청이 기대에 부응하지 못했습니다."),

    // 422 Unprocessable Entity - 서버가 요청을 이해했으나 처리할 수 없습니다.
    UNPROCESSABLE_ENTITY(HttpStatus.UNPROCESSABLE_ENTITY, "처리할 수 없는 엔터티입니다."),

    // 429 Too Many Requests - 클라이언트가 너무 많은 요청을 보냈습니다.
    TOO_MANY_REQUESTS(HttpStatus.TOO_MANY_REQUESTS, "요청이 너무 많습니다."),

    // 500 Internal Server Error - 서버 내부 오류가 발생했습니다.
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류가 발생했습니다."),

    // 501 Not Implemented - 서버가 요청을 처리할 기능을 구현하지 않았습니다.
    NOT_IMPLEMENTED(HttpStatus.NOT_IMPLEMENTED, "구현되지 않은 기능입니다."),

    // 502 Bad Gateway - 게이트웨이가 잘못된 응답을 수신했습니다.
    BAD_GATEWAY(HttpStatus.BAD_GATEWAY, "잘못된 게이트웨이입니다."),

    // 503 Service Unavailable - 서버가 요청을 처리할 수 없습니다 (과부하 또는 유지보수).
    SERVICE_UNAVAILABLE(HttpStatus.SERVICE_UNAVAILABLE, "서비스를 사용할 수 없습니다."),

    // 504 Gateway Timeout - 게이트웨이 시간이 초과되었습니다.
    GATEWAY_TIMEOUT(HttpStatus.GATEWAY_TIMEOUT, "게이트웨이 시간이 초과되었습니다.");

    private final HttpStatus status;
    private final String defaultMessage;



}

