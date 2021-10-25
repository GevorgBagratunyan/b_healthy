package com.blueteam.tracker.exception.search;

public class SearchNullParametersException extends RuntimeException{

    public SearchNullParametersException(String requiredParameters) {
        super("Required search parameters must not be null: -> " + requiredParameters);
    }
}
