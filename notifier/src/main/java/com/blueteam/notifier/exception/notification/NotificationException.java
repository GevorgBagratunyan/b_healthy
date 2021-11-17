package com.blueteam.notifier.exception.notification;

public class NotificationException extends RuntimeException{

    private final Object data;

    public NotificationException(String description, Object data) {
        super("An error occurred during the notification process ->" + description);
        this.data = data;
    }

    public Object getData() {
        return data;
    }

}
