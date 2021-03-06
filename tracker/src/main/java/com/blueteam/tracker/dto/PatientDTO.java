package com.blueteam.tracker.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

public class PatientDTO implements DTO{

    private Long patientId;
    @NotNull
    private Long objId;
    @Email
    private String email;
    private String name;
    @NotBlank
    private String phoneNumber;
    private Boolean isTracking;
    private List<DoctorDTO> doctors = new ArrayList<>();

    public PatientDTO() {
    }

    public PatientDTO(Long objId, String email, String phoneNumber) {
        this.objId = objId;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public List<DoctorDTO> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<DoctorDTO> doctors) {
        this.doctors = doctors;
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

    public Boolean getTracking() {
        return isTracking;
    }

    public void setTracking(Boolean tracking) {
        isTracking = tracking;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
