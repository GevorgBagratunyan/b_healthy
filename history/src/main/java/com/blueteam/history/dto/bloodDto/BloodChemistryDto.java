package com.blueteam.history.dto.bloodDto;

import com.blueteam.history.entity.Patient;
import com.blueteam.history.entity.history.exam.blood.BloodChemistry;

import java.time.LocalDate;

public class BloodChemistryDto {

    private BloodChemistryDto t;

    public BloodChemistryDto() {

    }

    public BloodChemistryDto(BloodChemistry bloodChemistry) {
        this.id = bloodChemistry.getId();
        this.analyzedDate = bloodChemistry.getAnalyzedDate();
        this.M_CREATININE = bloodChemistry.getM_CREATININE();
        this.F_CREATININE = bloodChemistry.getF_CREATININE();
        this.GLUCOSE = bloodChemistry.getGLUCOSE();
        this.TOTAL_BILIRUBIN = bloodChemistry.getTOTAL_BILIRUBIN();
        this.UNCONJUGATED_BILIRUBIN = bloodChemistry.getUNCONJUGATED_BILIRUBIN();
        this.CONJUGATED_BILIRUBIN = bloodChemistry.getCONJUGATED_BILIRUBIN();

    }

    private Long id;
    private LocalDate analyzedDate;
    private double M_CREATININE;
    private double F_CREATININE;
    private double GLUCOSE;
    private double TOTAL_BILIRUBIN;
    private double UNCONJUGATED_BILIRUBIN;
    private double CONJUGATED_BILIRUBIN;

    public BloodChemistry convertToEntity() {
        BloodChemistry chemistry = new BloodChemistry();
        chemistry.setAnalyzedDate(this.analyzedDate);
        chemistry.setId(this.id);
        chemistry.setCONJUGATED_BILIRUBIN(this.CONJUGATED_BILIRUBIN);
        chemistry.setGLUCOSE(this.GLUCOSE);
        chemistry.setF_CREATININE(this.F_CREATININE);
        chemistry.setM_CREATININE(this.M_CREATININE);
        chemistry.setTOTAL_BILIRUBIN(this.TOTAL_BILIRUBIN);
        chemistry.setUNCONJUGATED_BILIRUBIN(this.UNCONJUGATED_BILIRUBIN);

        return chemistry;
    }

    public BloodChemistryDto getT() {
        return t;
    }

    public void setT(BloodChemistryDto t) {
        this.t = t;
    }

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
}
