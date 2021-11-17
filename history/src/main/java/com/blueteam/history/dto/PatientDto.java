package com.blueteam.history.dto;

import com.blueteam.history.entity.Patient;
import com.blueteam.history.entity.history.History;
import com.sun.istack.NotNull;

import java.time.LocalDate;

public class PatientDto {

    public PatientDto() {

    }

    public PatientDto(Patient patient) {
        this.id = patient.getId();
        this.name = patient.getName();
        this.birthDate = patient.getBirthDate();
        this.phoneNumber = patient.getPhoneNumber();

    }
    private long id;
    @NotNull
    private String name;
    private LocalDate birthDate;

    private String phoneNumber;


    public Patient convertToEntity() {
        Patient patient = new Patient();
        patient.setBirthDate(this.birthDate);
        patient.setName(this.name);
        patient.setPhoneNumber(this.phoneNumber);

        return patient;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
