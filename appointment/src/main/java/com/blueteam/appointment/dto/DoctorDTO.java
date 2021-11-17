package com.blueteam.appointment.dto;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDTO implements DTO{

    private Long doctorId;
    @NotNull
    private Long objId;
    @NotEmpty
    private String name;
    @Email
    private String email;
    @NotBlank
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
