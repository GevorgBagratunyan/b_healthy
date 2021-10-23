package com.blueteam.tracker.service;

import com.blueteam.tracker.dto.DoctorDTO;
import com.blueteam.tracker.repository.ObservedPatientRepository;
import com.blueteam.tracker.repository.ObserverDoctorRepository;
import com.blueteam.tracker.service.crud.CRUD;
import org.springframework.stereotype.Service;

@Service
public class ObserverDoctorService implements CRUD<DoctorDTO, Long> {

    private final ObserverDoctorRepository observerDoctorRepository;
    private final ObservedPatientRepository observedPatientRepository;

    public ObserverDoctorService(ObserverDoctorRepository observerDoctorRepository,
                                 ObservedPatientRepository observedPatientRepository) {
        this.observerDoctorRepository = observerDoctorRepository;
        this.observedPatientRepository = observedPatientRepository;
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
