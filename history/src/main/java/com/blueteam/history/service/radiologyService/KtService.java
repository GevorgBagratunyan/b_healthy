package com.blueteam.history.service.radiologyService;

import com.blueteam.history.dto.PatientDto;
import com.blueteam.history.dto.radiologyDto.KtDto;
import com.blueteam.history.entity.Patient;
import com.blueteam.history.entity.history.exam.radiology.Kt;
import com.blueteam.history.repository.PatientRepo;
import com.blueteam.history.repository.exam.radiology.KtRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class KtService {

    @Autowired
    private KtRepo ktRepo;

    public void add(Kt kt) {
        ktRepo.save(kt);
    }

    public List<Kt> findAll() {
        return this.ktRepo.findAll();
    }

    public void update(KtDto ktDto) {

        Kt existingKt = this.ktRepo.getById(ktDto.getId());

        existingKt.setId(ktDto.getId());
        existingKt.setKtDate(ktDto.getKtDate());
        existingKt.setConclusion(ktDto.getConclusion());

        this.ktRepo.save(existingKt);
    }

    public void delete(long id) {
        this.ktRepo.deleteById(id);
    }
}
