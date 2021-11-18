package com.blueteam.history.service.treatmant;

import com.blueteam.history.dto.treatment.ConservativeTreatmentDto;
import com.blueteam.history.dto.treatment.SurggicalTreatmentDto;
import com.blueteam.history.entity.history.treatment.ConservativeTreatment;
import com.blueteam.history.entity.history.treatment.SurgicalTreatment;
import com.blueteam.history.repository.treatment.SurgucalTreatmentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SurgicalTreatmantService {

    private final SurgucalTreatmentRepo surgucalTreatmentRepo;

    public void add(SurgicalTreatment surgicalTreatment) {
        surgucalTreatmentRepo.save(surgicalTreatment);
    }
    public List<SurgicalTreatment> findAll() {
        return this.surgucalTreatmentRepo.findAll();
    }

    public void update(SurggicalTreatmentDto surggicalTreatmentDto) {

        SurgicalTreatment existingSurgicalTreatmant = this.surgucalTreatmentRepo.getById(surggicalTreatmentDto.getId());

        existingSurgicalTreatmant.setPatient(surggicalTreatmentDto.getPatient());
        existingSurgicalTreatmant.setSurgicalTreatment(surggicalTreatmentDto.getTreatment());
        existingSurgicalTreatmant.setDate(surggicalTreatmentDto.getDate());
        existingSurgicalTreatmant.setDoctor(surggicalTreatmentDto.getDoctor());

        this.surgucalTreatmentRepo.save(existingSurgicalTreatmant);
    }
    public void delete(long id) {
        this.surgucalTreatmentRepo.deleteById(id);
    }
}
