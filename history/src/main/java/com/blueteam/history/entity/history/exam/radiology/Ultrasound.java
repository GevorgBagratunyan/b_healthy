package com.blueteam.history.entity.history.exam.radiology;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "ultrasound")
public class Ultrasound {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "coclusion")
    private String coclusion;

    @Column(name = "date")
    private LocalDate ultrasoundDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCoclusion() {
        return coclusion;
    }

    public void setCoclusion(String coclusion) {
        this.coclusion = coclusion;
    }

    public LocalDate getUltrasoundDate() {
        return ultrasoundDate;
    }

    public void setUltrasoundDate(LocalDate ultrasoundDate) {
        this.ultrasoundDate = ultrasoundDate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ultrasound that = (Ultrasound) o;
        return Objects.equals(id, that.id) && Objects.equals(coclusion, that.coclusion) && Objects.equals(ultrasoundDate, that.ultrasoundDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, coclusion, ultrasoundDate);
    }
}


