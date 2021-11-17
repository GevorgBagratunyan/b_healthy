package com.blueteam.history.service;

import com.blueteam.history.dto.PatientDto;
import com.blueteam.history.entity.Patient;
import com.blueteam.history.repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class PatientService {

    @Autowired
    private PatientRepo patientRepo;


    public void add(Patient patient) {
        patientRepo.save(patient);
    }

    public List<Patient> findAll() {
        return this.patientRepo.findAll();
    }

    public void update(PatientDto patient) {

        Patient existingPatient = this.patientRepo.getById(patient.getId());

        existingPatient.setName(patient.getName());
        existingPatient.setPhoneNumber(patient.getPhoneNumber());
        existingPatient.setBirthDate(patient.getBirthDate());

        this.patientRepo.save(existingPatient);
    }

    public void delete(long id) {
        this.patientRepo.deleteById(id);
    }
}
