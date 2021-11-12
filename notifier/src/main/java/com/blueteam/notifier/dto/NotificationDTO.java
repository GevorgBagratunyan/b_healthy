package com.blueteam.notifier.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class NotificationDTO implements DTO{

    private String phoneNumber;
    @Email
    private String email;
    private String subject;
    private String msg;
    @NotNull
    private HemodynamicaDTO currentAvgHemodynamica;
    private String alertMsg;
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
