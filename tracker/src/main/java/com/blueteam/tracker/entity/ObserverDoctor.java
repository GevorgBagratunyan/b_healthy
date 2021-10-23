package com.blueteam.tracker.entity;

import com.blueteam.tracker.entity.observer.Observer;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "obs_doctor", schema = "tracker")
public class ObserverDoctor implements Observer {

    @Id
    @Column(name = "doctor_id")
    private Long id;

    @Column(name = "object_id")
    private Long obj_id;

    @ManyToMany
    @JoinTable(name = "obs_doctor_obs_patient",
    joinColumns = @JoinColumn(name = "obs_doctor_id"),
    inverseJoinColumns = @JoinColumn(name = "obs_patient_id"))
    private List<ObservedPatient> observedPatients = new ArrayList<>();

    @Override
    public void handleEvent(Hemodynamica hemodynamica, Long patientId, String msg) {
        System.out.println("Sending alert to NOTIFIER module");
        Integer heartRateAVG = hemodynamica.getHeartRate();
        Integer saturationAVG = hemodynamica.getSaturation();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime alertTime = LocalDateTime.now();
        System.err.println("Sending Email to doctor: " + this.id + "...");
        System.err.println(msg + ", Heart Rate is: " + heartRateAVG + ", Saturation is: " + saturationAVG);
        System.err.println(dtf.format(alertTime));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ObservedPatient> getObservedPatients() {
        return observedPatients;
    }

    public void setObservedPatients(List<ObservedPatient> observedPatients) {
        this.observedPatients = observedPatients;
    }

    public Long getObj_id() {
        return obj_id;
    }

    public void setObj_id(Long obj_id) {
        this.obj_id = obj_id;
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
                ", observedPatients=" + observedPatients +
                '}';
    }
}
