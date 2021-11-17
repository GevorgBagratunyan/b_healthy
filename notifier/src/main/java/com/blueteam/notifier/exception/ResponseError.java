package com.blueteam.notifier.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseError {

    private final String message;
    private final Object data;

    public ResponseError(String msg, Object data) {
        this.message = msg;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    @Override
    public String toString() {
        return "ResponseError{" +
                "message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
