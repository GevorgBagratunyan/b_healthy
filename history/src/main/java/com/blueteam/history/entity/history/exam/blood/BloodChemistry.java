package com.blueteam.history.entity.history.exam.blood;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Objects;

@Entity
@Table (name = "blood_chemistry")

public class BloodChemistry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column (name = "analyzed_date")
    private LocalDate analyzedDate;

    @Column(name = "m_creat")
    private double M_CREATININE; //0.6 to 1.2 mg/dL

    @Column(name = "f_creat")
    private double F_CREATININE; //0.5 to 1.3 mg/dL

    @Column(name = "glucose")
    private double GLUCOSE; //70 to 100 mg/dL

    @Column(name = "total_bil")
    private double TOTAL_BILIRUBIN; //0.1 to 1.2 mg/dL

    @Column(name = "uncon_bil")
    private double UNCONJUGATED_BILIRUBIN; // <0.9 mg/dl

    @Column(name = "con_bil")
    private double CONJUGATED_BILIRUBIN; // <0.3 mg/dl


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getM_CREATININE() {
        return M_CREATININE;
    }

    public void setM_CREATININE(double m_CREATININE) {
        M_CREATININE = m_CREATININE;
    }

    public double getF_CREATININE() {
        return F_CREATININE;
    }

    public void setF_CREATININE(double f_CREATININE) {
        F_CREATININE = f_CREATININE;
    }

    public double getGLUCOSE() {
        return GLUCOSE;
    }

    public void setGLUCOSE(double GLUCOSE) {
        this.GLUCOSE = GLUCOSE;
    }

    public double getTOTAL_BILIRUBIN() {
        return TOTAL_BILIRUBIN;
    }

    public void setTOTAL_BILIRUBIN(double TOTAL_BILIRUBIN) {
        this.TOTAL_BILIRUBIN = TOTAL_BILIRUBIN;
    }

    public double getUNCONJUGATED_BILIRUBIN() {
        return UNCONJUGATED_BILIRUBIN;
    }

    public void setUNCONJUGATED_BILIRUBIN(double UNCONJUGATED_BILIRUBIN) {
        this.UNCONJUGATED_BILIRUBIN = UNCONJUGATED_BILIRUBIN;
    }

    public double getCONJUGATED_BILIRUBIN() {
        return CONJUGATED_BILIRUBIN;
    }

    public void setCONJUGATED_BILIRUBIN(double CONJUGATED_BILIRUBIN) {
        this.CONJUGATED_BILIRUBIN = CONJUGATED_BILIRUBIN;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BloodChemistry that = (BloodChemistry) o;
        return Double.compare(that.M_CREATININE, M_CREATININE) == 0 && Double.compare(that.F_CREATININE, F_CREATININE) == 0 && Double.compare(that.GLUCOSE, GLUCOSE) == 0 && Double.compare(that.TOTAL_BILIRUBIN, TOTAL_BILIRUBIN) == 0 && Double.compare(that.UNCONJUGATED_BILIRUBIN, UNCONJUGATED_BILIRUBIN) == 0 && Double.compare(that.CONJUGATED_BILIRUBIN, CONJUGATED_BILIRUBIN) == 0 && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, M_CREATININE, F_CREATININE, GLUCOSE, TOTAL_BILIRUBIN, UNCONJUGATED_BILIRUBIN, CONJUGATED_BILIRUBIN);
    }

    public LocalDate getAnalyzedDate() {
        return analyzedDate;
    }

    public void setAnalyzedDate(LocalDate analyzedDate) {
        this.analyzedDate = analyzedDate;
    }
}
