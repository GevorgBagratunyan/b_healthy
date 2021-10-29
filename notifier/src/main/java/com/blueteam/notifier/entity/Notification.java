package com.blueteam.notifier.entity;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "patient_id")
    private Long objId;

    @Embedded
    private Hemodynamica hemodynamica;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "notification_id", referencedColumnName = "id")
    private List<DoctorContacts> doctorContacts;

    @Column(name = "notification_date")
    private LocalDateTime notificationDate;

    @PrePersist
    public void setNotificationDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        this.notificationDate = LocalDateTime.now().withNano(0);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getObjId() {
        return objId;
    }

    public void setObjId(Long objId) {
        this.objId = objId;
    }

    public Hemodynamica getHemodynamica() {
        return hemodynamica;
    }

    public void setHemodynamica(Hemodynamica hemodynamica) {
        this.hemodynamica = hemodynamica;
    }

//    public String getDoctorsPhoneNumber() {
//        return doctorsPhoneNumber;
//    }
//
//    public void setDoctorsPhoneNumber(String doctorsPhoneNumber) {
//        this.doctorsPhoneNumber = doctorsPhoneNumber;
//    }
//
//    public String getDoctorsEmail() {
//        return doctorsEmail;
//    }
//
//    public void setDoctorsEmail(String doctorsEmail) {
//        this.doctorsEmail = doctorsEmail;
//    }

    public LocalDateTime getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(LocalDateTime notificationDate) {
        this.notificationDate = notificationDate;
    }

    public List<DoctorContacts> getDoctorContacts() {
        return doctorContacts;
    }

    public void setDoctorContacts(List<DoctorContacts> doctorContacts) {
        this.doctorContacts = doctorContacts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notification that = (Notification) o;
        return Objects.equals(id, that.id) && Objects.equals(objId, that.objId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, objId);
    }
}
