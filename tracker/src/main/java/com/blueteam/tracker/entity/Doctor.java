package com.blueteam.tracker.entity;


import com.blueteam.tracker.entity.observer.ObserverDoctor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "doctor")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToOne(mappedBy = "doctor")
    @PrimaryKeyJoinColumn //Ids of Doctor And ObserverDoctor is the same
    private ObserverDoctor observerDoctor;

    @ManyToMany
    @JoinTable(name = "doctors_patients",
    joinColumns = @JoinColumn(name = "doctor_id"),
    inverseJoinColumns = @JoinColumn(name = "patient_id"))
    private List<Patient> patients = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public ObserverDoctor getObserverDoctor() {
        return observerDoctor;
    }

    public void setObserverDoctor(ObserverDoctor observerDoctor) {
        this.observerDoctor = observerDoctor;
    }
}
