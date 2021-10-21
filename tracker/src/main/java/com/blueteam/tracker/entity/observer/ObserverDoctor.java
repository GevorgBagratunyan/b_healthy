package com.blueteam.tracker.entity.observer;

import com.blueteam.tracker.entity.Doctor;
import com.blueteam.tracker.entity.Hemodynamica;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "obs_doctor")
public class ObserverDoctor implements Observer{

    @Id
    @Column(name = "doctor_id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId //Ids of Doctor And ObserverDoctor is the same
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToMany
    @JoinTable(name = "obs_doctor_obs_patient",
    joinColumns = @JoinColumn(name = "obs_doctor_id"),
    inverseJoinColumns = @JoinColumn(name = "obs_patient_id"))
    private List<ObservedPatient> observedPatients = new ArrayList<>();

    @Override
    public void handleEvent(Hemodynamica hemodynamica, Long patientId) {
        System.out.println("Sending alert to NOTIFIER module");
        Integer heartRateAVG = hemodynamica.getHeartRate();
        Integer saturationAVG = hemodynamica.getSaturation();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime alertTime = LocalDateTime.now();
        System.err.println("Sending Email to doctor: " + this.doctor.getName()+ "...");
        System.err.println("Heart Rate is: " + heartRateAVG + ", Saturation is: " + saturationAVG);
        System.err.println(dtf.format(alertTime));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public List<ObservedPatient> getObservedPatients() {
        return observedPatients;
    }

    public void setObservedPatients(List<ObservedPatient> observedPatients) {
        this.observedPatients = observedPatients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObserverDoctor that = (ObserverDoctor) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ObserverDoctor{" +
                "id=" + id +
                ", doctor=" + doctor +
                ", observedPatients=" + observedPatients +
                '}';
    }
}
