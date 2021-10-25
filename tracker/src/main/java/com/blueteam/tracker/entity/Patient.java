package com.blueteam.tracker.entity;

import com.blueteam.tracker.entity.observer.Observed;
import com.blueteam.tracker.entity.observer.Observer;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "patient")
public class Patient implements Observed {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "object_id")
    private Long objId;

    @ManyToMany(mappedBy = "patients")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Doctor> doctors = new HashSet<>();

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Hemodynamica> hemodynamics = new LinkedList<>();

    @Column(name = "observation_started")
    private LocalDate observationStarted;

    @Column(name = "observation_ended")
    private LocalDate observationEnded;

    @Column(name = "updated_date")
    private LocalDate updatedDate;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @PrePersist
    public void setObservedDate() {
        observationStarted = LocalDate.now();
    }

    @PreUpdate
    public void setUpdatedDate() {
        updatedDate = LocalDate.now();
    }

    @Override
    public void addObserver(Observer observer) {
        doctors.add((Doctor) observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        doctors.remove((Doctor) observer);
        if(doctors.isEmpty()) {
            observationEnded = LocalDate.now();
        }
    }

    @Override
    public void notifyObservers(String msg) {
        Hemodynamica avg = calculateAvgHemodynamica();
        for (Doctor d : doctors) {
            d.handleEvent(avg, id, msg);
        }
    }

    public void redAlert() {
        notifyObservers("WARNING!!! Red Alert from patient, call 911");
    }

    //Notifies all observing doctors when hemodynamic parameters are dangerous
    public void addHemodynamicParameter(Hemodynamica hemodynamica) {
        this.hemodynamics.add(hemodynamica);
        //if a significant amount of information is recorded, remove (first)old 100 records
        //7days*24hours*60minutes = 10080
        if(this.hemodynamics.size() > 10080) {
            this.hemodynamics.subList(0, 100).clear();
        }
        if(this.hemodynamics.size()<10) {
            return;
        }
        if (isDangerous()) {
            String msg = "Hemodynamic parameters are dangerous";
            notifyObservers(msg);
        }
    }

    private boolean isDangerous() {
        Hemodynamica avgHemodynamica = calculateAvgHemodynamica();
        Integer heartRateAVG = avgHemodynamica.getHeartRate();
        Integer saturationAVG = avgHemodynamica.getSaturation();

        return heartRateAVG > 90 || heartRateAVG < 60 || saturationAVG < 93;
    }

    //Calculates AVG params from last 10 records in the List
    private Hemodynamica calculateAvgHemodynamica() {
        Integer sumHR = 0;
        Integer sumSAT = 0;
        for (int i = 1; i<11; i++) {
            Hemodynamica hemodynamica = hemodynamics.get(hemodynamics.size()-i);
            sumHR += hemodynamica.getHeartRate();
            sumSAT += hemodynamica.getSaturation();
        }
        Integer heartRateAVG = sumHR / 10;
        Integer saturationAVG = sumSAT / 10;

        return new Hemodynamica(saturationAVG, heartRateAVG);
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
                ", observerDoctors=" + doctors +
                '}';
    }
}
