package com.blueteam.tracker.entity;

import com.blueteam.tracker.entity.observer.Observer;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "doctor")
public class Doctor implements Observer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "object_id")
    private Long obj_id;

    @ManyToMany
    @JoinTable(name = "doctor_patient",
    joinColumns = @JoinColumn(name = "doctor_id"),
    inverseJoinColumns = @JoinColumn(name = "patient_id"))
    private List<Patient> patients = new ArrayList<>();

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

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

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public Long getObj_id() {
        return obj_id;
    }

    public void setObj_id(Long obj_id) {
        this.obj_id = obj_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor that = (Doctor) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", obj_id=" + obj_id +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
