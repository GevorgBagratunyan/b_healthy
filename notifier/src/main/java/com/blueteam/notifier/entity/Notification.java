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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "notification_id")
    private DoctorContacts doctorContacts;

    @Column(name = "notification_date")
    private LocalDateTime notificationDate;

    @PrePersist
    public void setNotificationDate() {
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

    public DoctorContacts getDoctorContacts() {
        return doctorContacts;
    }

    public void setDoctorContacts(DoctorContacts doctorContacts) {
        this.doctorContacts = doctorContacts;
    }

    public LocalDateTime getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(LocalDateTime notificationDate) {
        this.notificationDate = notificationDate;
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
