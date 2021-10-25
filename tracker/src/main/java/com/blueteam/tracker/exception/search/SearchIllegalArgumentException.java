package com.blueteam.tracker.exception.search;

public class SearchIllegalArgumentException extends RuntimeException{

    public SearchIllegalArgumentException(String args) {
        super("Illegal search arguments: -> " + args);
    }
}
