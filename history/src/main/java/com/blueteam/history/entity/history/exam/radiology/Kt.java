package com.blueteam.history.entity.history.exam.radiology;

import com.blueteam.history.entity.Doctor;
import com.blueteam.history.entity.Patient;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "kt")
public class Kt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(name = "coclusion")
    private String conclusion;

    @Column(name = "kt_date")
    private LocalDate ktDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public LocalDate getKtDate() {
        return ktDate;
    }

    public void setKtDate(LocalDate ktDate) {
        this.ktDate = ktDate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Kt kt = (Kt) o;
        return Objects.equals(id, kt.id) && Objects.equals(conclusion, kt.conclusion) && Objects.equals(ktDate, kt.ktDate) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, conclusion, ktDate);
    }
}
