package com.blueteam.history.entity.history.exam.radiology;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "mrt")
public class Mrt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(name= "coclusion")
    private String cunclusion;

    @Column(name= "date")
    private LocalDate mrtDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCunclusion() {
        return cunclusion;
    }

    public void setCunclusion(String cunclusion) {
        this.cunclusion = cunclusion;
    }

    public LocalDate getDate() {
        return mrtDate;
    }

    public void setDate(LocalDate date) {
        this.mrtDate = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mrt mrt = (Mrt) o;
        return Objects.equals(id, mrt.id) && Objects.equals(cunclusion, mrt.cunclusion) && Objects.equals(mrtDate, mrt.mrtDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cunclusion, mrtDate);
    }
}
