package com.blueteam.history.dto;

public class ApiError {
    private final int code;
    private final String message;

    public ApiError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
