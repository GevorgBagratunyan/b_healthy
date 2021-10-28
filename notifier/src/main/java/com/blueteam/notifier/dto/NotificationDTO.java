package com.blueteam.notifier.dto;

import javax.validation.constraints.NotNull;
import java.util.Map;

public class NotificationDTO implements DTO{

    private Map<String, String> emailsAndPhoneNumbers;
    private HemodynamicaDTO currentAvgHemodynamica;
    private String alertMsg;
    @NotNull
    private Long objId; //Patient's id

    public Map<String, String> getEmailsAndPhoneNumbers() {
        return emailsAndPhoneNumbers;
    }

    public void setEmailsAndPhoneNumbers(Map<String, String> emailsAndPhoneNumbers) {
        this.emailsAndPhoneNumbers = emailsAndPhoneNumbers;
    }

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
}
