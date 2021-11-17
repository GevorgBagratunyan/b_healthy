package com.blueteam.history.service.radiologyService;

import com.blueteam.history.dto.radiologyDto.MrtDto;
import com.blueteam.history.entity.history.exam.radiology.Mrt;
import com.blueteam.history.repository.exam.radiology.MrtRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MrtService {

    @Autowired
    private MrtRepo mrtRepo;

    public void add(Mrt mrt) {
        this.mrtRepo.save(mrt);
    }

    public List<Mrt> findAll() {
        return this.mrtRepo.findAll();
    }

    public void update(MrtDto mrtDto) {

        Mrt existingMrt = this.mrtRepo.getById(mrtDto.getId());

        existingMrt.setId(mrtDto.getId());
        existingMrt.setDate(mrtDto.getMrtDate());
        existingMrt.setCunclusion(mrtDto.getConclusion());

        this.mrtRepo.save(existingMrt);
    }

    public void delete(long id) {
        this.mrtRepo.deleteById(id);
    }
}

