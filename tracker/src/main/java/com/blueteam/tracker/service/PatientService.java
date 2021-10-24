package com.blueteam.tracker.service;

import com.blueteam.tracker.dto.HemodynamicaDTO;
import com.blueteam.tracker.dto.PatientDTO;
import com.blueteam.tracker.entity.Hemodynamica;
import com.blueteam.tracker.entity.Patient;
import com.blueteam.tracker.entity.Doctor;
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

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PatientService implements CRUD<PatientDTO, Long> {

    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public PatientService(DoctorRepository doctorRepository,
                          PatientRepository patientRepository) {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }


    @Override
    public void create(PatientDTO patientDTO) {
        Patient patient = new Patient();
        BeanUtils.copyProperties(patientDTO, patient);
        patientRepository.save(patient);
    }

    @Override
    public void delete(Long id) {
        patientRepository.deleteById(id);
    }

    @Override
    public PatientDTO get(Long id) {
        Patient patient =  patientRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
        PatientDTO patientDTO = new PatientDTO();
        BeanUtils.copyProperties(patient, patientDTO);
        DtoMapper.mapDoctorsToDoctorDTOs(patient, patientDTO);

        return patientDTO;
    }

    @Override
    public void update(PatientDTO patientDTO, Long id) {
        Patient patient = patientRepository.findById(id)
                        .orElseThrow(NoSuchElementException::new);
        BeanUtils.copyProperties(patientDTO, patient);
        patientRepository.save(patient);
    }

    public List<PatientDTO> getAll(SearchCriteria criteria) {
        CriteriaValidator.validateCriteria(criteria);
        Pageable pageable = PageableCreator.createPageable(criteria);
        List<Patient> patients = patientRepository.findAll(pageable).getContent();
        return DtoMapper.mapToPatientDTOs(patients);
    }

    public void trackHemodynamicParams(HemodynamicaDTO hemodynamicaDTO, Long id) {
       Patient patient = patientRepository.findById(id)
               .orElseThrow(NoSuchElementException::new);
       Hemodynamica hemodynamica = new Hemodynamica();
       BeanUtils.copyProperties(hemodynamicaDTO, hemodynamica);
       hemodynamica.setObservedPatient(patient);
       patient.addHemodynamicParameter(hemodynamica);
       patientRepository.save(patient);
    }

    public void addDoctor(Long doctorId, Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(NoSuchElementException::new);
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(NoSuchElementException::new);
        patient.addObserver(doctor);
        patientRepository.save(patient);
    }

    public void removeDoctor(Long doctorId, Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(NoSuchElementException::new);
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(NoSuchElementException::new);
        patient.removeObserver(doctor);
        patientRepository.save(patient);
    }

    public void redAlert(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
        patient.redAlert();
    }

    public List<PatientDTO> getPatientsByDoctorId(Long doctorId){
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(NoSuchElementException::new);
        List<Patient> patients = doctor.getPatients();
        return DtoMapper.mapToPatientDTOs(patients);
    }

    public List<Hemodynamica> getCurrentHemodynamics(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
        return patient.getHemodynamics();
    }
}
