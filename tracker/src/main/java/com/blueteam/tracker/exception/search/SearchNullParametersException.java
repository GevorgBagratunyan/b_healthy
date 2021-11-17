package com.blueteam.tracker.exception.search;

public class SearchNullParametersException extends RuntimeException{

    private final Object data;
    public SearchNullParametersException(String requiredParameters, Object data) {
        super("Required search parameters must not be null: -> " + requiredParameters);
        this.data = data;
    }

    public Object getData() {
        return data;
    }
}
