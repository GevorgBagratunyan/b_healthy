package com.blueteam.history.dto.bloodDto;

import com.blueteam.history.entity.Patient;
import com.blueteam.history.entity.history.exam.blood.BloodGeneral;

import java.time.LocalDate;

public class BloodGeneralDto {

    public BloodGeneralDto(){

    }
    private Long id;
    private LocalDate analyzedDate;
    private double M_RED_BLOOD_CELLS;
    private double F_RED_BLOOD_CELLS;
    private double WHITE_BLOOD_CELLS;
    private double M_HEMOGLOBIN;
    private double F_HEMOGLOBIN;
    private double M_ESR;
    private double F_ESR;

    public BloodGeneralDto(BloodGeneral bloodGeneral) {
        this.id = bloodGeneral.getId();
        this.analyzedDate = bloodGeneral.getAnalyzedDate();
        this.M_RED_BLOOD_CELLS = bloodGeneral.getM_RED_BLOOD_CELLS();
        this.F_RED_BLOOD_CELLS = bloodGeneral.getF_RED_BLOOD_CELLS();
        this.WHITE_BLOOD_CELLS = bloodGeneral.getWHITE_BLOOD_CELLS();
        this.M_HEMOGLOBIN = bloodGeneral.getM_HEMOGLOBIN();
        this.F_HEMOGLOBIN = bloodGeneral.getF_HEMOGLOBIN();
        this.M_ESR  = bloodGeneral.getM_ESR();
        this.F_ESR = bloodGeneral.getF_ESR();
    }

    public BloodGeneral convertToEntity() {
        BloodGeneral bloodGeneral = new BloodGeneral();
        bloodGeneral.setId(this.id);
        bloodGeneral.setAnalyzedDate(this.analyzedDate);
        bloodGeneral.setM_RED_BLOOD_CELLS(this.M_RED_BLOOD_CELLS);
        bloodGeneral.setF_RED_BLOOD_CELLS(this.F_RED_BLOOD_CELLS);
        bloodGeneral.setWHITE_BLOOD_CELLS(this.WHITE_BLOOD_CELLS);
        bloodGeneral.setM_HEMOGLOBIN(this.M_HEMOGLOBIN);
        bloodGeneral.setF_HEMOGLOBIN(this.F_HEMOGLOBIN);
        bloodGeneral.setM_ESR(this.M_ESR);
        bloodGeneral.setF_ESR(this.F_ESR);

        return bloodGeneral;
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
}
