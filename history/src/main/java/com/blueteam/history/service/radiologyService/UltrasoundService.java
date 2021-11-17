package com.blueteam.history.service.radiologyService;

import com.blueteam.history.dto.radiologyDto.KtDto;
import com.blueteam.history.dto.radiologyDto.UltrasoundDto;
import com.blueteam.history.entity.history.exam.radiology.Kt;
import com.blueteam.history.entity.history.exam.radiology.Ultrasound;
import com.blueteam.history.repository.exam.radiology.KtRepo;
import com.blueteam.history.repository.exam.radiology.UltrasoundRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class UltrasoundService {

    @Autowired
    private UltrasoundRepo ultrasoundRepo;

    public void add(Ultrasound ultrasound) {
        ultrasoundRepo.save(ultrasound);
    }

    public List<Ultrasound> findAll() {
        return this.ultrasoundRepo.findAll();
    }

    public void update(UltrasoundDto ultrasoundDto) {

        Ultrasound existingUltrasound = this.ultrasoundRepo.getById(ultrasoundDto.getId());

        existingUltrasound.setUltrasoundDate(ultrasoundDto.getUltrasoundDate());
        existingUltrasound.setId(ultrasoundDto.getId());
        existingUltrasound.setCoclusion(ultrasoundDto.getConclusion());

        this.ultrasoundRepo.save(existingUltrasound);
    }

    public void delete(long id) {
        this.ultrasoundRepo.deleteById(id);
    }
}
