package com.blueteam.notifier.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class DoctorContacts {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "phone_number")
    private String doctorsPhoneNumber = "";

    @Column(name = "email")
    private String doctorsEmail = "";

    public Long getId() {
        return id;
    }

    public String getDoctorsPhoneNumber() {
        return doctorsPhoneNumber;
    }

    public String getDoctorsEmail() {
        return doctorsEmail;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDoctorsPhoneNumber(String doctorsPhoneNumber) {
        this.doctorsPhoneNumber = doctorsPhoneNumber;
    }

    public void setDoctorsEmail(String doctorsEmail) {
        this.doctorsEmail = doctorsEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoctorContacts that = (DoctorContacts) o;
        return Objects.equals(id, that.id) && Objects.equals(doctorsPhoneNumber, that.doctorsPhoneNumber) && Objects.equals(doctorsEmail, that.doctorsEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, doctorsPhoneNumber, doctorsEmail);
    }
}
