package com.blueteam.notifier.dto;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class SmsAuthenticationDto implements DTO{

    @NotEmpty
    @Size(min = 4, max = 4)
    private String verificationString;
    @NotEmpty
    @Pattern(regexp = "^(\\d{3}[- .]?){2}\\d{4}$",
            message = "Phone number must contain digits, whitespaces, dots or hyphens (-)")
    private String phoneNumber;

    public String getVerificationString() {
        return verificationString;
    }

    public void setVerificationString(String verificationString) {
        this.verificationString = verificationString;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
