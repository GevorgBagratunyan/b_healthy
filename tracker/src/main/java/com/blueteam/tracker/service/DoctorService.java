package com.blueteam.tracker.service;

import com.blueteam.tracker.dto.DoctorDTO;
import com.blueteam.tracker.dto.PatientDTO;
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

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
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
    @Transactional
    public DoctorDTO create(DoctorDTO doctorDTO) {
        Doctor doctor = new Doctor();
        BeanUtils.copyProperties(doctorDTO, doctor);
        Doctor saved = doctorRepository.save(doctor);
        DoctorDTO responseDTO = new DoctorDTO();
        BeanUtils.copyProperties(saved, responseDTO);
        responseDTO.setDoctorId(saved.getId());
        return responseDTO;
    }

    @Override
    @Transactional
    public DoctorDTO delete(Long id) {
        DoctorDTO responseDTO = new DoctorDTO();
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new DoctorNotFoundException(id.toString(), id));
        List<Patient> patients = doctor.getPatients();
        for (Patient p : patients) {
            p.removeObserver(doctor);
        }
        BeanUtils.copyProperties(doctor, responseDTO);
        responseDTO.setDoctorId(doctor.getId());
        DtoMapper.mapPatientsToPatientDTOs(doctor, responseDTO);
        doctorRepository.deleteById(id);
        return responseDTO;
    }

    @Override
    public DoctorDTO get(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new DoctorNotFoundException(id.toString(), id));
        DoctorDTO doctorDTO = new DoctorDTO();
        BeanUtils.copyProperties(doctor, doctorDTO);
        doctorDTO.setDoctorId(doctor.getId());
        DtoMapper.mapPatientsToPatientDTOs(doctor, doctorDTO);
        return doctorDTO;
    }

    @Override
    @Transactional
    public DoctorDTO update(DoctorDTO doctorDTO, Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new DoctorNotFoundException(id.toString(), id));
        BeanUtils.copyProperties(doctorDTO, doctor);
        Doctor saved = doctorRepository.save(doctor);
        DoctorDTO responseDTO = new DoctorDTO();
        BeanUtils.copyProperties(saved, responseDTO);
        responseDTO.setDoctorId(saved.getId());
        return responseDTO;

    }

    public Set<DoctorDTO> getAll(SearchCriteria criteria) {
        CriteriaValidator.validateCriteria(criteria);
        Pageable pageable = PageableCreator.createPageable(criteria);
        List<Doctor> doctors = doctorRepository.findAll(pageable).getContent();
        Set<Doctor> docs = new HashSet<>(doctors);
        return DtoMapper.mapToDoctorDTOs(docs);
    }

    @Transactional
    public DoctorDTO addPatient(Long doctorId, Long patientId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new DoctorNotFoundException(doctorId.toString(), doctorId));
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new PatientNotFoundException(patientId.toString(), patientId));
        doctor.addPatient(patient);
        patient.addObserver(doctor);
        patientRepository.save(patient);
        Doctor saved = doctorRepository.save(doctor);
        DoctorDTO responseDTO = new DoctorDTO();
        PatientDTO patientDTO = new PatientDTO();
        BeanUtils.copyProperties(patient, patientDTO);
        BeanUtils.copyProperties(saved, responseDTO);
        responseDTO.getPatients().add(patientDTO);
        responseDTO.setDoctorId(saved.getId());
        return responseDTO;
    }

    @Transactional
    public DoctorDTO removeObservedPatient(Long doctorId, Long patientId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new DoctorNotFoundException(doctorId.toString(), doctorId));
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new PatientNotFoundException(patientId.toString(), patientId));
        doctor.getPatients().remove(patient);
        Doctor saved = doctorRepository.save(doctor);
        DoctorDTO responseDTO = new DoctorDTO();
        BeanUtils.copyProperties(saved, responseDTO);
        responseDTO.setDoctorId(saved.getId());
        return responseDTO;
    }

    public Set<DoctorDTO> getDoctorsByPatientId(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException(id.toString(), id));
        Set<Doctor> doctors = patient.getDoctors();
        return DtoMapper.mapToDoctorDTOs(doctors);
    }
}

