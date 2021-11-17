package com.blueteam.history.entity.history;

import com.blueteam.history.entity.Patient;
import com.blueteam.history.entity.history.exam.Exam;
import com.blueteam.history.entity.history.treatment.ConservativeTreatment;
import com.blueteam.history.entity.history.treatment.SurgicalTreatment;


import javax.persistence.Entity;
import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "history")
public class History {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "conservative_treatment")
    private ConservativeTreatment conservativeTreatment;

    @OneToOne
    @JoinColumn(name = "surgical_treatment")
    private SurgicalTreatment surgicalTreatment;

    @OneToOne
    @JoinColumn(name = "exam")
    private Exam exam;

    @ManyToOne
    @JoinColumn(name = "patient")
    private Patient patient;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        History history = (History) o;
        return Objects.equals(id, history.id) && Objects.equals(conservativeTreatment, history.conservativeTreatment) && Objects.equals(surgicalTreatment, history.surgicalTreatment) && Objects.equals(exam, history.exam) && Objects.equals(patient, history.patient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, conservativeTreatment, surgicalTreatment, exam, patient);
    }
}
