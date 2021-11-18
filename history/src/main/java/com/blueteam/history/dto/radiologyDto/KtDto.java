package com.blueteam.history.dto.radiologyDto;

import com.blueteam.history.entity.history.exam.radiology.Kt;
import lombok.Data;

import java.time.LocalDate;
@Data
public class KtDto {

    public KtDto() {

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
}
