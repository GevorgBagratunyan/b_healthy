package com.blueteam.history.dto.radiologyDto;


import com.blueteam.history.entity.history.exam.radiology.Mrt;
import lombok.Data;

import java.time.LocalDate;
@Data
public class MrtDto {
    public MrtDto(){

    }
    private Long id;
    private String conclusion;
    private LocalDate mrtDate;

    public MrtDto(Mrt mrt) {
        this.id = mrt.getId();
        this.conclusion = mrt.getCunclusion();
        this.mrtDate = mrt.getMrtDate();
    }


    public Mrt convertToEntity() {
        Mrt mrt = new Mrt();
        mrt.setCunclusion(this.conclusion);
        mrt.setId(this.id);
        mrt.setMrtDate(this.mrtDate);
        return mrt;
    }

}
