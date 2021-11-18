package com.blueteam.history.dto.treatment;

import com.blueteam.history.entity.Doctor;
import com.blueteam.history.entity.Patient;
import com.blueteam.history.entity.history.treatment.ConservativeTreatment;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ConservativeTreatmentDto {

    public ConservativeTreatmentDto(){

    }
    private Long id;
    private Patient patient;
    private Doctor doctor;
    private String treatment;
    private LocalDate date;

    public ConservativeTreatmentDto(ConservativeTreatment conservativeTreatment) {
        this.id = conservativeTreatment.getId();
        this.patient = conservativeTreatment.getPatient();
        this.doctor = conservativeTreatment.getDoctor();
        this.treatment = conservativeTreatment.getTreatment();
    }

    public ConservativeTreatment convertToEntity() {
        ConservativeTreatment conservativeTreatment = new ConservativeTreatment();
        conservativeTreatment.setPatient(this.patient);
        conservativeTreatment.setDoctor(this.doctor);
        conservativeTreatment.setTreatment(this.treatment);
        conservativeTreatment.setDate(this.date);

        return conservativeTreatment;
    }
}
