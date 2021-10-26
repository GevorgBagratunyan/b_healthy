package com.blueteam.tracker.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
    private List<PatientDTO> patients;

    public DoctorDTO() {
    }

    public DoctorDTO(Long objId, String email, String phoneNumber) {
        this.objId = objId;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public List<PatientDTO> getPatients() {
        return patients;
    }

    public void setPatients(List<PatientDTO> patients) {
        this.patients = patients;
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
}
