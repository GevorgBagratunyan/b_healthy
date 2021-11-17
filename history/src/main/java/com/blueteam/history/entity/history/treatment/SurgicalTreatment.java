package com.blueteam.history.entity.history.treatment;

import com.blueteam.history.entity.Doctor;
import com.blueteam.history.entity.Patient;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "surgical_treatment")
public class SurgicalTreatment {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private  Long id;
    @Column(name = "date")
    private LocalDate date;

    @OneToOne
    @JoinColumn(name = "surgical_id")
    private Patient patient;

    @OneToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @Column(name= "surgical_treatment")
    private String surgicalTreatment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getSurgicalTreatment() {
        return surgicalTreatment;
    }

    public void setSurgicalTreatment(String surgicalTreatment) {
        this.surgicalTreatment = surgicalTreatment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SurgicalTreatment that = (SurgicalTreatment) o;
        return Objects.equals(id, that.id) && Objects.equals(date, that.date) && Objects.equals(patient, that.patient) && Objects.equals(doctor, that.doctor) && Objects.equals(surgicalTreatment, that.surgicalTreatment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, patient, doctor, surgicalTreatment);
    }
}
