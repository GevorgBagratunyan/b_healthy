package com.blueteam.history.dto.bloodDto;

import com.blueteam.history.entity.history.exam.blood.BloodGeneral;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BloodGeneralDto {

    public BloodGeneralDto() {

    }

    private Long id;
    private LocalDate analyzedDate;
    private Double mRedBloodCells;
    private double fRedBloodCells;
    private double whiteBloodCells;
    private double mHemoglobin;
    private double fHemoglobin;
    private double mEsr;
    private double fEsr;

    public BloodGeneralDto(BloodGeneral bloodGeneral) {
        this.id = bloodGeneral.getId();
        this.analyzedDate = bloodGeneral.getAnalyzedDate();
        this.mRedBloodCells = bloodGeneral.getMRedBloodCells();
        this.fRedBloodCells = bloodGeneral.getFRedBloodCells();
        this.whiteBloodCells = bloodGeneral.getWhiteBloodCells();
        this.mHemoglobin = bloodGeneral.getMHemoglobin();
        this.fHemoglobin = bloodGeneral.getFHemoglobin();
        this.mEsr = bloodGeneral.getMEsr();
        this.fEsr = bloodGeneral.getFEsr();
    }

    public BloodGeneral convertToEntity() {
        BloodGeneral bloodGeneral = new BloodGeneral();
        bloodGeneral.setId(this.id);
        bloodGeneral.setAnalyzedDate(this.analyzedDate);
        bloodGeneral.setMRedBloodCells(this.mRedBloodCells);
        bloodGeneral.setFRedBloodCells(this.fRedBloodCells);
        bloodGeneral.setWhiteBloodCells(this.whiteBloodCells);
        bloodGeneral.setMHemoglobin(this.mHemoglobin);
        bloodGeneral.setFHemoglobin(this.fHemoglobin);
        bloodGeneral.setMEsr(this.mEsr);
        bloodGeneral.setFEsr(this.fEsr);

        return bloodGeneral;
    }


}
