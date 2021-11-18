package com.blueteam.history.service.treatmant;

import com.blueteam.history.dto.PatientDto;
import com.blueteam.history.dto.treatment.ConservativeTreatmentDto;
import com.blueteam.history.entity.Patient;
import com.blueteam.history.entity.history.treatment.ConservativeTreatment;
import com.blueteam.history.repository.treatment.ConservativeTreatmentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ConservativeTreatmantService {

    private final ConservativeTreatmentRepo conservativeTreatmentRepo;

    public void add(ConservativeTreatment conservativeTreatment) {
        conservativeTreatmentRepo.save(conservativeTreatment);
    }

    public List<ConservativeTreatment> findAll() {
        return this.conservativeTreatmentRepo.findAll();
    }

    public void update(ConservativeTreatmentDto conservativeTreatmentDto) {

        ConservativeTreatment existingConsTreatmant = this.conservativeTreatmentRepo.getById(conservativeTreatmentDto.getId());

        existingConsTreatmant.setPatient(conservativeTreatmentDto.getPatient());
        existingConsTreatmant.setTreatment(conservativeTreatmentDto.getTreatment());
        existingConsTreatmant.setDate(conservativeTreatmentDto.getDate());
        existingConsTreatmant.setDoctor(conservativeTreatmentDto.getDoctor());

        this.conservativeTreatmentRepo.save(existingConsTreatmant);
    }

    public void delete(long id) {
        this.conservativeTreatmentRepo.deleteById(id);
    }
}

