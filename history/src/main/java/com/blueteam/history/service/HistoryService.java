package com.blueteam.history.service;

import com.blueteam.history.dto.HistoryDto;
import com.blueteam.history.entity.Patient;
import com.blueteam.history.entity.history.History;
import com.blueteam.history.repository.HistoryRepo;
import com.blueteam.history.repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class HistoryService {
    @Autowired
    private HistoryRepo historyRepo;


    public void add(History history) {
        historyRepo.save(history);
    }

    public List<History> findAll() {
        return this.historyRepo.findAll();
    }

    public void update(HistoryDto historyDto) {
        History exitingHistory = historyRepo.getById(historyDto.getId());

    }
}
