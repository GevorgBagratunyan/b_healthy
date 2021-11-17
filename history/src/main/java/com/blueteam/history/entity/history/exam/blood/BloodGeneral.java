package com.blueteam.history.entity.history.exam.blood;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "blood_general")
public class BloodGeneral {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "analyzed_date")
    private LocalDate analyzedDate;

    @Column(name = "m_rbc")
    private double M_RED_BLOOD_CELLS;

    @Column(name = "f_rbc")
    private double F_RED_BLOOD_CELLS;

    @Column(name = "wbc")
    private double WHITE_BLOOD_CELLS;

    @Column(name = "m_hb")
    private double M_HEMOGLOBIN;

    @Column(name = "f_hb")
    private double F_HEMOGLOBIN;

    @Column(name = "m_esr")
    private double M_ESR;

    @Column(name = "f_esr")
    private double F_ESR;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getAnalyzedDate() {
        return analyzedDate;
    }

    public void setAnalyzedDate(LocalDate analyzedDate) {
        this.analyzedDate = analyzedDate;
    }

    public double getM_RED_BLOOD_CELLS() {
        return M_RED_BLOOD_CELLS;
    }

    public void setM_RED_BLOOD_CELLS(double m_RED_BLOOD_CELLS) {
        M_RED_BLOOD_CELLS = m_RED_BLOOD_CELLS;
    }

    public double getF_RED_BLOOD_CELLS() {
        return F_RED_BLOOD_CELLS;
    }

    public void setF_RED_BLOOD_CELLS(double f_RED_BLOOD_CELLS) {
        F_RED_BLOOD_CELLS = f_RED_BLOOD_CELLS;
    }

    public double getWHITE_BLOOD_CELLS() {
        return WHITE_BLOOD_CELLS;
    }

    public void setWHITE_BLOOD_CELLS(double WHITE_BLOOD_CELLS) {
        this.WHITE_BLOOD_CELLS = WHITE_BLOOD_CELLS;
    }

    public double getM_HEMOGLOBIN() {
        return M_HEMOGLOBIN;
    }

    public void setM_HEMOGLOBIN(double m_HEMOGLOBIN) {
        M_HEMOGLOBIN = m_HEMOGLOBIN;
    }

    public double getF_HEMOGLOBIN() {
        return F_HEMOGLOBIN;
    }

    public void setF_HEMOGLOBIN(double f_HEMOGLOBIN) {
        F_HEMOGLOBIN = f_HEMOGLOBIN;
    }

    public double getM_ESR() {
        return M_ESR;
    }

    public void setM_ESR(double m_ESR) {
        M_ESR = m_ESR;
    }

    public double getF_ESR() {
        return F_ESR;
    }

    public void setF_ESR(double f_ESR) {
        F_ESR = f_ESR;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BloodGeneral that = (BloodGeneral) o;
        return Double.compare(that.M_RED_BLOOD_CELLS, M_RED_BLOOD_CELLS) == 0 && Double.compare(that.F_RED_BLOOD_CELLS, F_RED_BLOOD_CELLS) == 0 && Double.compare(that.WHITE_BLOOD_CELLS, WHITE_BLOOD_CELLS) == 0 && Double.compare(that.M_HEMOGLOBIN, M_HEMOGLOBIN) == 0 && Double.compare(that.F_HEMOGLOBIN, F_HEMOGLOBIN) == 0 && Double.compare(that.M_ESR, M_ESR) == 0 && Double.compare(that.F_ESR, F_ESR) == 0 && Objects.equals(id, that.id) && Objects.equals(analyzedDate, that.analyzedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, analyzedDate, M_RED_BLOOD_CELLS, F_RED_BLOOD_CELLS, WHITE_BLOOD_CELLS, M_HEMOGLOBIN, F_HEMOGLOBIN, M_ESR, F_ESR);
    }
}
