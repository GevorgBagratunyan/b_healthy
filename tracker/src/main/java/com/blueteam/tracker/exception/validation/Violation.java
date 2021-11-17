package com.blueteam.tracker.exception.validation;

public class Violation {

    private final String field;
    private final String message;

    public Violation(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}
