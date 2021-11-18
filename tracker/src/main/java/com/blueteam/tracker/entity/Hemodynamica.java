package com.blueteam.tracker.entity;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "hemodynamica", indexes = @Index(name = "patient_index", columnList = "patient_id ASC"))
public class Hemodynamica {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "saturation", nullable = false)
    private Integer saturation;

    @Column(name = "heart_rate", nullable = false)
    private Integer heartRate;

    @Column(name = "date")
    private LocalDateTime date;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    public Hemodynamica() {
    }

    public Hemodynamica(Integer saturation, Integer heartRate) {
        this.saturation = saturation;
        this.heartRate = heartRate;
    }

    @PrePersist
    public void setDate() {
        this.date = LocalDateTime.now().withNano(0);
    }

    public Long getId() {
        return id;
    }

    public Integer getSaturation() {
        return saturation;
    }

    public void setSaturation(Integer saturation) {
        this.saturation = saturation;
    }

    public Integer getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(Integer heartRate) {
        this.heartRate = heartRate;
    }

    public Patient getObservedPatient() {
        return patient;
    }

    public void setObservedPatient(Patient patient) {
        this.patient = patient;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
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
        Hemodynamica that = (Hemodynamica) o;
        return Objects.equals(id, that.id) && Objects.equals(saturation, that.saturation) && Objects.equals(heartRate, that.heartRate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, saturation, heartRate);
    }
}
