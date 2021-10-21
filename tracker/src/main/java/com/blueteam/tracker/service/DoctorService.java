package com.blueteam.tracker.service;

import com.blueteam.tracker.dto.DoctorDTO;
import com.blueteam.tracker.repository.DoctorRepository;
import com.blueteam.tracker.service.crud.CRUD;
import org.springframework.stereotype.Service;

@Service
public class DoctorService implements CRUD<DoctorDTO, Long> {

    private final DoctorRepository repository;

    public DoctorService(DoctorRepository repository) {
        this.repository = repository;
    }

    @Override
    public void create(DoctorDTO doctorDTO) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public DoctorDTO get(Long id) {
        return null;
    }

    @Override
    public void update(DoctorDTO doctorDTO) {

    }
}
