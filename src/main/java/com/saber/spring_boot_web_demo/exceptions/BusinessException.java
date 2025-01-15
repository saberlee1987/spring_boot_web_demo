package com.saber.spring_boot_web_demo.exceptions;

public class BusinessException extends RuntimeException {
    private final Short code;
    private final String message;

    public BusinessException(Short code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public Short getCode() {
        return code;
    }
}
