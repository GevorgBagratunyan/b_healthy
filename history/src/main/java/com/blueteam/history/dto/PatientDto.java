package com.blueteam.history.dto;

import com.blueteam.history.entity.Patient;
import com.blueteam.history.entity.history.History;
import com.sun.istack.NotNull;
import lombok.Data;

import java.time.LocalDate;
@Data
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

}
