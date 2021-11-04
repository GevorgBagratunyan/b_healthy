package com.blueteam.appointment.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

public class DoctorDTO implements DTO{

    private Long doctorId;
    @NotNull
    private Long objId;
    @Email
    private String email;
    @NotBlank
    @Pattern(regexp = "^(\\d{3}[- .]?){2}\\d{4}$") //allow optional whitespace, dots, or hyphens (-)
    private String phoneNumber;
    private List<AppointmentDTO> appointmentDTOs = new ArrayList<>();

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public Long getObjId() {
        return objId;
    }

    public void setObjId(Long objId) {
        this.objId = objId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<AppointmentDTO> getAppointmentDTOs() {
        return appointmentDTOs;
    }

    public void setAppointmentDTOs(List<AppointmentDTO> appointmentDTOs) {
        this.appointmentDTOs = appointmentDTOs;
    }
}
