package com.blueteam.tracker.entity.observer;

import com.blueteam.tracker.entity.Doctor;
import com.blueteam.tracker.entity.Hemodynamica;
import com.blueteam.tracker.entity.Patient;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "obs_patient")
public class ObservedPatient implements Observed{

    @Id
    @Column(name = "patient_id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId //Ids of ObservedPatient and Patient is the same
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToMany(mappedBy = "observedPatients")
    private List<ObserverDoctor> observerDoctors = new ArrayList<>();

    @OneToMany(mappedBy = "observedPatient", cascade = CascadeType.ALL,
            fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Hemodynamica> hemodynamics = new ArrayList<>();

    @Override
    public void addObserver(Observer observer) {
        patient.getDoctors().add((Doctor) observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        patient.getDoctors().add((Doctor) observer);
    }

    @Override
    public void notifyObservers() {
        Hemodynamica avg = calculateAvgHemodynamica();
        for (Observer d : observerDoctors) {
            d.handleEvent(avg, id);
        }
        hemodynamics.clear();
    }

    private void RedAlert() {
        notifyObservers();
    }

    //Notifies all observing doctors when hemodynamic parameters are dangerous
    public void addHemodynamicParameter(Hemodynamica hemodynamica) {
        hemodynamics.add(hemodynamica);
        //Always keeps the list with 10 params, like queue FIFO
        if(hemodynamics.size()==11) {
            hemodynamics.remove(0);
        } else return; //do not check isDangerous if size<10
        //if already collected 10 parameters, check danger level
        if (isDangerous()) {
            notifyObservers();
        }
    }

    private boolean isDangerous() {
        Hemodynamica avgHemodynamica = calculateAvgHemodynamica();
        Integer heartRateAVG = avgHemodynamica.getHeartRate();
        Integer saturationAVG = avgHemodynamica.getSaturation();

        return heartRateAVG > 90 || heartRateAVG < 60 || saturationAVG < 93;
    }

    private Hemodynamica calculateAvgHemodynamica() {
        Integer sumHR = 0;
        Integer sumSAT = 0;
        for (Hemodynamica h : hemodynamics) {
            sumHR += h.getHeartRate();
            sumSAT += h.getSaturation();
        }
        Integer heartRateAVG = sumHR / hemodynamics.size();
        Integer saturationAVG = sumSAT / hemodynamics.size();

        return new Hemodynamica(saturationAVG, heartRateAVG);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<ObserverDoctor> getObserverDoctors() {
        return observerDoctors;
    }

    public void setObserverDoctors(List<ObserverDoctor> observerDoctors) {
        this.observerDoctors = observerDoctors;
    }

    public List<Hemodynamica> getHemodynamics() {
        return hemodynamics;
    }

    public void setHemodynamics(List<Hemodynamica> hemodynamics) {
        this.hemodynamics = hemodynamics;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObservedPatient that = (ObservedPatient) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ObservedPatient{" +
                "id=" + id +
                ", patient=" + patient +
                ", observerDoctors=" + observerDoctors +
                '}';
    }
}
