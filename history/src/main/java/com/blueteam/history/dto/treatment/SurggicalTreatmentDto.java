package com.blueteam.history.dto.treatment;

import com.blueteam.history.entity.Doctor;
import com.blueteam.history.entity.Patient;
import com.blueteam.history.entity.history.treatment.ConservativeTreatment;
import com.blueteam.history.entity.history.treatment.SurgicalTreatment;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SurggicalTreatmentDto {
    public  SurggicalTreatmentDto(){

    }
    private Long id;
    private Patient patient;
    private Doctor doctor;
    private String treatment;
    private LocalDate date;

    public SurggicalTreatmentDto(SurgicalTreatment surgicalTreatment) {
        this.id = surgicalTreatment.getId();
        this.patient = surgicalTreatment.getPatient();
        this.doctor = surgicalTreatment.getDoctor();
        this.treatment = surgicalTreatment.getSurgicalTreatment();
    }

    public SurgicalTreatment convertToEntity() {
        SurgicalTreatment surgicalTreatment = new SurgicalTreatment();
        surgicalTreatment.setPatient(this.patient);
        surgicalTreatment.setDoctor(this.doctor);
        surgicalTreatment.setSurgicalTreatment(this.treatment);
        surgicalTreatment.setDate(this.date);

        return surgicalTreatment;
    }
}
