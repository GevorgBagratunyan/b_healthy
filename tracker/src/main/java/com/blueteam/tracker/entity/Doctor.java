package com.blueteam.tracker.entity;

import com.blueteam.tracker.controller.RestTemplateClient;
import com.blueteam.tracker.dto.HemodynamicaDTO;
import com.blueteam.tracker.dto.NotificationDTO;
import com.blueteam.tracker.entity.observer.Observer;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
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
    private Long objId;

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
    public void handleEvent(Hemodynamica hemodynamica, Long objId, String msg) {
        NotificationDTO notificationDTO = new NotificationDTO();
        HemodynamicaDTO hemodynamicaDTO = new HemodynamicaDTO();
        BeanUtils.copyProperties(hemodynamica, hemodynamicaDTO);
        notificationDTO.setAlertMsg(msg);
        notificationDTO.setCurrentAvgHemodynamica(hemodynamicaDTO);
        notificationDTO.setObjId(objId);
        notificationDTO.setEmail(email);
        notificationDTO.setPhoneNumber(phoneNumber);
        RestTemplateClient.sendNotification(notificationDTO);
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

    public Long getObjId() {
        return objId;
    }

    public void setObjId(Long objId) {
        this.objId = objId;
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
                ", objId=" + objId +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
