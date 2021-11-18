package com.blueteam.history.service.bloodService;

import com.blueteam.history.dto.bloodDto.BloodGeneralDto;
import com.blueteam.history.entity.history.exam.blood.BloodGeneral;
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

        existingBloodGeneral.setFEsr(bloodGeneralDto.getFEsr());
        existingBloodGeneral.setMEsr(bloodGeneralDto.getMEsr());
        existingBloodGeneral.setWhiteBloodCells(bloodGeneralDto.getWhiteBloodCells());
        existingBloodGeneral.setFRedBloodCells(bloodGeneralDto.getFRedBloodCells());
        existingBloodGeneral.setMRedBloodCells(bloodGeneralDto.getMRedBloodCells());
        existingBloodGeneral.setFHemoglobin(bloodGeneralDto.getFHemoglobin());
        existingBloodGeneral.setMHemoglobin(bloodGeneralDto.getMHemoglobin());
        existingBloodGeneral.setAnalyzedDate(bloodGeneralDto.getAnalyzedDate());

        this.bloodGeneralRepo.save(existingBloodGeneral);
    }

    public void delete(long id) {
        this.bloodGeneralRepo.deleteById(id);
    }
}
