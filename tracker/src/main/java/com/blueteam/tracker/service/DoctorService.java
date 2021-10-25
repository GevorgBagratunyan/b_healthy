package com.blueteam.tracker.service;

import com.blueteam.tracker.dto.DoctorDTO;
import com.blueteam.tracker.entity.Patient;
import com.blueteam.tracker.entity.Doctor;
import com.blueteam.tracker.exception.doctor.DoctorNotFoundException;
import com.blueteam.tracker.exception.patient.PatientNotFoundException;
import com.blueteam.tracker.repository.PatientRepository;
import com.blueteam.tracker.repository.DoctorRepository;
import com.blueteam.tracker.service.criteria.SearchCriteria;
import com.blueteam.tracker.service.crud.CRUD;
import com.blueteam.tracker.service.util.CriteriaValidator;
import com.blueteam.tracker.service.util.DtoMapper;
import com.blueteam.tracker.service.util.PageableCreator;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class DoctorService implements CRUD<DoctorDTO, Long> {

    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public DoctorService(DoctorRepository doctorRepository,
                         PatientRepository patientRepository) {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public void create(DoctorDTO doctorDTO) {
        Doctor doctor = new Doctor();
        BeanUtils.copyProperties(doctorDTO, doctor);
        doctorRepository.save(doctor);
    }

    @Override
    public void delete(Long id) {
        doctorRepository.deleteById(id);
    }

    @Override
    public DoctorDTO get(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new DoctorNotFoundException(id.toString()));
        DoctorDTO doctorDTO = new DoctorDTO();
        BeanUtils.copyProperties(doctor, doctorDTO);
        DtoMapper.mapPatientsToPatientDTOs(doctor, doctorDTO);
        return doctorDTO;
    }

    @Override
    public void update(DoctorDTO doctorDTO, Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new DoctorNotFoundException(id.toString()));
        BeanUtils.copyProperties(doctorDTO, doctor);
        doctorRepository.save(doctor);
    }

    public Set<DoctorDTO> getAll(SearchCriteria criteria) {
        CriteriaValidator.validateCriteria(criteria);
        Pageable pageable = PageableCreator.createPageable(criteria);
        List<Doctor> doctors = doctorRepository.findAll(pageable).getContent();
        Set<Doctor> docs = new HashSet<>(doctors);
        return  DtoMapper.mapToDoctorDTOs(docs);
    }

    public void addPatient(Long doctorId, Long patientId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new DoctorNotFoundException(doctorId.toString()));
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new PatientNotFoundException(patientId.toString()));
        doctor.getPatients().add(patient);
        doctorRepository.save(doctor);
    }

    public void removeObservedPatient(Long doctorId, Long patientId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new DoctorNotFoundException(doctorId.toString()));
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new PatientNotFoundException(patientId.toString()));
        doctor.getPatients().remove(patient);
        doctorRepository.save(doctor);
    }

    public Set<DoctorDTO> getDoctorsByPatientId(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException(id.toString()));
        Set<Doctor> doctors = patient.getDoctors();
        return DtoMapper.mapToDoctorDTOs(doctors);
    }
}

