package com.blueteam.appointment.dto;

public class NotificationDTO {
    private String phoneNumber;
    private String email;
    private String subject;
    private String msg;

    public NotificationDTO(String phoneNumber, String email, String subject, String msg) {
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.subject = subject;
        this.msg = msg;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
