package com.blueteam.history.dto.radiologyDto;

import com.blueteam.history.entity.history.exam.radiology.Ultrasound;

import java.time.LocalDate;

public class UltrasoundDto {

    public UltrasoundDto() {

    }

    private Long id;
    private String conclusion;
    private LocalDate ultrasoundDate;

    public UltrasoundDto(Ultrasound ultrasound) {
        this.id = ultrasound.getId();
        this.conclusion = ultrasound.getCoclusion();
        this.ultrasoundDate = ultrasound.getUltrasoundDate();
    }

    public Ultrasound convertToEntity() {
        Ultrasound ultrasound = new Ultrasound();
        ultrasound.setUltrasoundDate(this.ultrasoundDate);
        ultrasound.setId(this.id);
        ultrasound.setCoclusion(this.conclusion);
        return ultrasound;
    }

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

    public LocalDate getUltrasoundDate() {
        return ultrasoundDate;
    }

    public void setUltrasoundDate(LocalDate ultrasoundDate) {
        this.ultrasoundDate = ultrasoundDate;
    }
}