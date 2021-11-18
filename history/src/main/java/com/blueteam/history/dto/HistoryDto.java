package com.blueteam.history.dto;

import com.blueteam.history.entity.Patient;
import com.blueteam.history.entity.history.History;
import com.blueteam.history.entity.history.exam.Exam;
import com.blueteam.history.entity.history.treatment.ConservativeTreatment;
import com.blueteam.history.entity.history.treatment.SurgicalTreatment;
import lombok.Data;

@Data
public class HistoryDto {

    public HistoryDto() {

    }

    public HistoryDto(History history) {
        this.id = history.getId();
        this.conservativeTreatment = history.getConservativeTreatment();
        this.surgicalTreatment = history.getSurgicalTreatment();
        this.exam = history.getExam();
        this.patient = history.getPatient();
    }

    private Long id;
    private ConservativeTreatment conservativeTreatment;
    private SurgicalTreatment surgicalTreatment;
    private Exam exam;
    private Patient patient;

    public History convertToEntity() {
        History history = new History();
        history.setId(this.id);
        history.setExam(this.exam);
        history.setConservativeTreatment(this.conservativeTreatment);
        history.setSurgicalTreatment(this.surgicalTreatment);

        return history;
    }


}

