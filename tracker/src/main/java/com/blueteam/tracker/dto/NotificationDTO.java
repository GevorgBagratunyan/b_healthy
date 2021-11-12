package com.blueteam.tracker.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Map;

public class NotificationDTO implements DTO{


    private String phoneNumber;
    private String email;
    private String name;
    private HemodynamicaDTO currentAvgHemodynamica;
    private String alertMsg;
    private Long objId;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
