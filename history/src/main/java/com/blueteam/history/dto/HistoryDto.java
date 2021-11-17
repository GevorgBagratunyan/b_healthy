package com.blueteam.history.dto;

import com.blueteam.history.entity.Patient;
import com.blueteam.history.entity.history.History;
import com.blueteam.history.entity.history.exam.Exam;
import com.blueteam.history.entity.history.treatment.ConservativeTreatment;
import com.blueteam.history.entity.history.treatment.SurgicalTreatment;

public class HistoryDto {

    public HistoryDto() {

    }

    public HistoryDto(History history) {
        this.id = history.getId();
        this.conservativeTreatment = history.getConservativeTreatment();
        this.surgicalTreatment = history.getSurgicalTreatment();
        this.exam = history.getExam();
        this.patient = history.getPatient();
    }

    private Long id;
    private ConservativeTreatment conservativeTreatment;
    private SurgicalTreatment surgicalTreatment;
    private Exam exam;
    private Patient patient;

    public History convertToEntity() {
        History history = new History();
        history.setId(this.id);
        history.setExam(this.exam);
        history.setConservativeTreatment(this.conservativeTreatment);
        history.setSurgicalTreatment(this.surgicalTreatment);

        return history;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ConservativeTreatment getConservativeTreatment() {
        return conservativeTreatment;
    }

    public void setConservativeTreatment(ConservativeTreatment conservativeTreatment) {
        this.conservativeTreatment = conservativeTreatment;
    }

    public SurgicalTreatment getSurgicalTreatment() {
        return surgicalTreatment;
    }

    public void setSurgicalTreatment(SurgicalTreatment surgicalTreatment) {
        this.surgicalTreatment = surgicalTreatment;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}

