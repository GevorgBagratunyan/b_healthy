package com.blueteam.history.service.bloodService;

import com.blueteam.history.dto.PatientDto;
import com.blueteam.history.dto.bloodDto.BloodGeneralDto;
import com.blueteam.history.entity.Patient;
import com.blueteam.history.entity.history.exam.blood.BloodGeneral;
import com.blueteam.history.repository.PatientRepo;
import com.blueteam.history.repository.exam.Blood.BloodGeneralRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BloodGeneralService {


    @Autowired
    private BloodGeneralRepo bloodGeneralRepo;

    public void add(BloodGeneral bloodGeneral) {
        bloodGeneralRepo.save(bloodGeneral);
    }

    public List<BloodGeneral> findAll() {
        return this.bloodGeneralRepo.findAll();
    }

    public void update(BloodGeneralDto bloodGeneralDto) {

        BloodGeneral existingBloodGeneral = this.bloodGeneralRepo.getById(bloodGeneralDto.getId());

        existingBloodGeneral.setId(bloodGeneralDto.getId());
        existingBloodGeneral.setF_ESR(bloodGeneralDto.getF_ESR());
        existingBloodGeneral.setM_ESR(bloodGeneralDto.getM_ESR());
        existingBloodGeneral.setWHITE_BLOOD_CELLS(bloodGeneralDto.getWHITE_BLOOD_CELLS());
        existingBloodGeneral.setF_RED_BLOOD_CELLS(bloodGeneralDto.getF_RED_BLOOD_CELLS());
        existingBloodGeneral.setM_RED_BLOOD_CELLS(bloodGeneralDto.getM_RED_BLOOD_CELLS());
        existingBloodGeneral.setF_HEMOGLOBIN(bloodGeneralDto.getF_HEMOGLOBIN());
        existingBloodGeneral.setM_HEMOGLOBIN(bloodGeneralDto.getM_HEMOGLOBIN());
        existingBloodGeneral.setAnalyzedDate(bloodGeneralDto.getAnalyzedDate());

        this.bloodGeneralRepo.save(existingBloodGeneral);
    }

    public void delete(long id) {
        this.bloodGeneralRepo.deleteById(id);
    }
}
