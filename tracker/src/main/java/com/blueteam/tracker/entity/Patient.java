package com.blueteam.tracker.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "patient")
public class Patient{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "object_id")
    private Long objId;

    @ManyToMany(mappedBy = "patients", fetch = FetchType.LAZY)
    private Set<Doctor> doctors = new HashSet<>();

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Hemodynamica> hemodynamics = new LinkedList<>();

    @Column(name = "observation_started")
    private LocalDate observationStarted;

    @Column(name = "observation_ended")
    private LocalDate observationEnded;

    @Column(name = "updated_date")
    private LocalDate updatedDate;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "is_tracking")
    private Boolean isTracking;

    @PrePersist
    public void setObservedDate() {
        observationStarted = LocalDate.now();
    }

    @PreUpdate
    public void setUpdatedDate() {
        updatedDate = LocalDate.now();
    }

    public void addObserver(Doctor observer) {
        doctors.add(observer);
    }

    public void removeObserver(Doctor observer) {
        doctors.remove(observer);
        if(doctors.isEmpty()) {
            observationEnded = LocalDate.now();
        }
    }

    public void addHemodynamicParameter(Hemodynamica hemodynamica) {
        this.hemodynamics.add(hemodynamica);
        //if a significant amount of information is recorded, remove (first)old 100 records
        //This list keeps records for 7 days -> 7days*24hours*60minutes = 10080
        if (this.hemodynamics.size() > 10080) {
            this.hemodynamics.subList(0, 100).clear();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(Set<Doctor> doctors) {
        this.doctors = doctors;
    }

    public List<Hemodynamica> getHemodynamics() {
        return hemodynamics;
    }

    public void setHemodynamics(List<Hemodynamica> hemodynamics) {
        this.hemodynamics = hemodynamics;
    }

    public Long getObjId() {
        return objId;
    }

    public void setObjId(Long objId) {
        this.objId = objId;
    }

    public LocalDate getObservationStarted() {
        return observationStarted;
    }

    public void setObservationStarted(LocalDate observationStarted) {
        this.observationStarted = observationStarted;
    }

    public LocalDate getObservationEnded() {
        return observationEnded;
    }

    public void setObservationEnded(LocalDate observationEnded) {
        this.observationEnded = observationEnded;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
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

    public Boolean getTracking() {
        return isTracking;
    }

    public void setTracking(Boolean tracking) {
        isTracking = tracking;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient that = (Patient) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", objId=" + objId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", isTracking=" + isTracking +
                '}';
    }
}
