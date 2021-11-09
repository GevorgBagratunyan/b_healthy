package com.blueteam.notifier.dto;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class SmsAuthenticationDto implements DTO{

    @NotEmpty
    @Size(min = 4, max = 4)
    private String verificationCode;
    @NotEmpty
    private String phoneNumber;

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
