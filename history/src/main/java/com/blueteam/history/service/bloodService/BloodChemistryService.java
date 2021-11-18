package com.blueteam.history.service.bloodService;

import com.blueteam.history.dto.bloodDto.BloodChemistryDto;
import com.blueteam.history.entity.history.exam.blood.BloodChemistry;
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

        existingBloodChemitry.setUnconjugatedBilirubin(chemistryDto.getUncongjugatedBilirubin());
        existingBloodChemitry.setId(chemistryDto.getId());
        existingBloodChemitry.setTotalBilirubin(chemistryDto.getTotalBilirubin());
        existingBloodChemitry.setGlucose(chemistryDto.getGlucose());
        existingBloodChemitry.setMCreatinine(chemistryDto.getMCreatinine());
        existingBloodChemitry.setFCreatinine(chemistryDto.getFCreatinine());
        existingBloodChemitry.setAnalyzedDate(chemistryDto.getAnalyzedDate());
        existingBloodChemitry.setConjugatedBilirubin(chemistryDto.getCongjugatedBilirubin());

        this.chemistryRepo.save(existingBloodChemitry);
    }

    public void delete(long id) {
        this.chemistryRepo.deleteById(id);
    }
}


