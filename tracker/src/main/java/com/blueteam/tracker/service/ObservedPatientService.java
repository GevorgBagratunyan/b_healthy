package com.blueteam.tracker.service;

import com.blueteam.tracker.dto.PatientDTO;
import com.blueteam.tracker.entity.Hemodynamica;
import com.blueteam.tracker.entity.ObservedPatient;
import com.blueteam.tracker.repository.ObservedPatientRepository;
import com.blueteam.tracker.repository.ObserverDoctorRepository;
import com.blueteam.tracker.service.crud.CRUD;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ObservedPatientService implements CRUD<PatientDTO, Long> {

    private final ObserverDoctorRepository observerDoctorRepository;
    private final ObservedPatientRepository observedPatientRepository;

    public ObservedPatientService(ObserverDoctorRepository observerDoctorRepository,
                                  ObservedPatientRepository observedPatientRepository) {
        this.observerDoctorRepository = observerDoctorRepository;
        this.observedPatientRepository = observedPatientRepository;
    }


    @Override
    public void create(PatientDTO patientDTO) {

    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public PatientDTO get(Long aLong) {
        return null;
    }

    @Override
    public void update(PatientDTO patientDTO) {

    }

    public void trackHemodynamicParams(Hemodynamica hemodynamica, Long id) {
       ObservedPatient observedPatient = observedPatientRepository.findById(id)
               .orElseThrow(NoSuchElementException::new);
       hemodynamica.setObservedPatient(observedPatient);
       observedPatient.addHemodynamicParameter(hemodynamica);
       observedPatientRepository.save(observedPatient);
    }
}
