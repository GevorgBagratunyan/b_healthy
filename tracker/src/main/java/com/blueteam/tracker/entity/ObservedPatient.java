package com.blueteam.tracker.entity;

import com.blueteam.tracker.entity.observer.Observed;
import com.blueteam.tracker.entity.observer.Observer;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "obs_patient", schema = "tracker")
public class ObservedPatient implements Observed {

    @Id
    @Column(name = "patient_id")
    private Long id;

    @Column(name = "object_id")
    private Long objId;

    @ManyToMany(mappedBy = "observedPatients")
    private List<ObserverDoctor> observerDoctors = new ArrayList<>();

    @OneToMany(mappedBy = "observedPatient", cascade = CascadeType.ALL,
            fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Hemodynamica> hemodynamics = new ArrayList<>();

    @Override
    public void addObserver(Observer observer) {
        observerDoctors.add((ObserverDoctor) observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observerDoctors.remove((ObserverDoctor) observer);
    }

    @Override
    public void notifyObservers(String msg) {
        Hemodynamica avg = calculateAvgHemodynamica();
        for (Observer d : observerDoctors) {
            d.handleEvent(avg, id, msg);
        }
        hemodynamics.clear();
    }

    private void redAlert() {
        notifyObservers("WARNING!!! Red Alert from patient, call 911");
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

    public Long getObjId() {
        return objId;
    }

    public void setObjId(Long objId) {
        this.objId = objId;
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
                ", observerDoctors=" + observerDoctors +
                '}';
    }
}
