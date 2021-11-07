package com.blueteam.tracker.exception.search;

public class SearchIllegalArgumentException extends RuntimeException{

    private final Object data;

    public SearchIllegalArgumentException(String args, Object data) {
        super("Illegal search arguments: -> " + args);
        this.data = data;
    }

    public Object getData() {
        return data;
    }
}
