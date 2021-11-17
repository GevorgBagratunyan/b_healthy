package com.blueteam.history.dto.radiologyDto;


import com.blueteam.history.entity.history.exam.radiology.Mrt;

import java.time.LocalDate;

public class MrtDto {
    public MrtDto(){

    }
    private Long id;
    private String conclusion;
    private LocalDate mrtDate;

    public MrtDto(Mrt mrt) {
        this.id = mrt.getId();
        this.conclusion = mrt.getCunclusion();
        this.mrtDate = mrt.getDate();
    }


    public Mrt convertToEntity() {
        Mrt mrt = new Mrt();
        mrt.setCunclusion(this.conclusion);
        mrt.setId(this.id);
        mrt.setDate(this.mrtDate);

        return mrt;
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

    public LocalDate getMrtDate() {
        return mrtDate;
    }

    public void setMrtDate(LocalDate mrtDate) {
        this.mrtDate = mrtDate;
    }
}
