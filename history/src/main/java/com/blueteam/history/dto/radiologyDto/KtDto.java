package com.blueteam.history.dto.radiologyDto;

import com.blueteam.history.entity.Patient;
import com.blueteam.history.entity.history.exam.radiology.Kt;

import java.time.LocalDate;

public class KtDto {

    public KtDto(){

    }
    private Long id;
    private String conclusion;
    private LocalDate ktDate;

    public KtDto(Kt kt) {
        this.id = kt.getId();
        this.conclusion = kt.getConclusion();
        this.ktDate = kt.getKtDate();
    }


    public Kt convertToEntity() {
        Kt kt = new Kt();
        kt.setConclusion(this.conclusion);
        kt.setId(this.id);
        kt.setKtDate(this.ktDate);

        return kt;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getKtDate() {
        return ktDate;
    }

    public void setKtDate(LocalDate ktDate) {
        this.ktDate = ktDate;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }
}
