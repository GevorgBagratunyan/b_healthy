package com.blueteam.history.service.bloodService;

import com.blueteam.history.dto.PatientDto;
import com.blueteam.history.dto.bloodDto.BloodChemistryDto;
import com.blueteam.history.entity.Patient;
import com.blueteam.history.entity.history.exam.blood.BloodChemistry;
import com.blueteam.history.repository.PatientRepo;
import com.blueteam.history.repository.exam.Blood.BloodChemistryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BloodChemistryService {


    @Autowired
    private BloodChemistryRepo chemistryRepo;

    public void add(BloodChemistry chemistry) {
        chemistryRepo.save(chemistry);
    }

    public List<BloodChemistry> findAll() {
        return this.chemistryRepo.findAll();
    }

    public void update(BloodChemistryDto chemistryDto) {

        BloodChemistry existingBloodChemitry = this.chemistryRepo.getById(chemistryDto.getId());

        existingBloodChemitry.setUNCONJUGATED_BILIRUBIN(chemistryDto.getUNCONJUGATED_BILIRUBIN());
        existingBloodChemitry.setId(chemistryDto.getId());
        existingBloodChemitry.setTOTAL_BILIRUBIN(chemistryDto.getTOTAL_BILIRUBIN());
        existingBloodChemitry.setGLUCOSE(chemistryDto.getGLUCOSE());
        existingBloodChemitry.setM_CREATININE(chemistryDto.getM_CREATININE());
        existingBloodChemitry.setF_CREATININE(chemistryDto.getF_CREATININE());
        existingBloodChemitry.setAnalyzedDate(chemistryDto.getAnalyzedDate());
        existingBloodChemitry.setCONJUGATED_BILIRUBIN(chemistryDto.getCONJUGATED_BILIRUBIN());

        this.chemistryRepo.save(existingBloodChemitry);
    }

    public void delete(long id) {
        this.chemistryRepo.deleteById(id);
    }
}


