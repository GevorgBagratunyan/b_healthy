package com.blueteam.notifier.dto;

import javax.validation.constraints.NotNull;

public class NotificationDTO implements DTO{

    private String phoneNumber;
    private String email;
    private String subject;
    private String msg;
    private HemodynamicaDTO currentAvgHemodynamica;
    private String alertMsg;
    @NotNull
    private Long objId; //Patient's id

    public HemodynamicaDTO getCurrentAvgHemodynamica() {
        return currentAvgHemodynamica;
    }

    public void setCurrentAvgHemodynamica(HemodynamicaDTO currentAvgHemodynamica) {
        this.currentAvgHemodynamica = currentAvgHemodynamica;
    }

    public String getAlertMsg() {
        return alertMsg;
    }

    public void setAlertMsg(String alertMsg) {
        this.alertMsg = alertMsg;
    }

    public Long getObjId() {
        return objId;
    }

    public void setObjId(Long objId) {
        this.objId = objId;
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
