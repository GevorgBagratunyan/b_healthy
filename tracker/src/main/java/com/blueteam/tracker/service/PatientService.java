package com.blueteam.tracker.service;

import com.blueteam.tracker.dto.DoctorDTO;
import com.blueteam.tracker.dto.HemodynamicaDTO;
import com.blueteam.tracker.dto.PatientDTO;
import com.blueteam.tracker.entity.Hemodynamica;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public PatientDTO create(PatientDTO patientDTO) {
        Patient patient = new Patient();
        BeanUtils.copyProperties(patientDTO, patient);
        Patient saved = patientRepository.save(patient);
        PatientDTO responseDTO = new PatientDTO();
        BeanUtils.copyProperties(saved, responseDTO);
        responseDTO.setPatientId(saved.getId());
        return responseDTO;
    }

    @Override
    public PatientDTO delete(Long id) {
        PatientDTO responseDTO = new PatientDTO();
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException(id.toString()));
        BeanUtils.copyProperties(patient, responseDTO);
        responseDTO.setPatientId(patient.getId());
        patientRepository.deleteById(id);
        return responseDTO;
    }

    @Override
    public PatientDTO get(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException(id.toString()));
        PatientDTO responseDTO = new PatientDTO();
        BeanUtils.copyProperties(patient, responseDTO);
        responseDTO.setPatientId(patient.getId());
        DtoMapper.mapDoctorsToDoctorDTOs(patient, responseDTO);
        return responseDTO;
    }

    @Override
    public PatientDTO update(PatientDTO patientDTO, Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException(id.toString()));
        BeanUtils.copyProperties(patientDTO, patient);
        Patient saved = patientRepository.save(patient);
        PatientDTO responseDTO = new PatientDTO();
        BeanUtils.copyProperties(saved, responseDTO);
        responseDTO.setPatientId(saved.getId());
        return responseDTO;
    }

    public List<PatientDTO> getAll(SearchCriteria criteria) {
        CriteriaValidator.validateCriteria(criteria);
        Pageable pageable = PageableCreator.createPageable(criteria);
        Page<Patient> page = patientRepository.findAll(pageable);
        List<Patient> patients = page.getContent();
        return DtoMapper.mapToPatientDTOs(patients);
    }

    public void trackHemodynamicParams(HemodynamicaDTO hemodynamicaDTO, Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException(id.toString()));
        Hemodynamica hemodynamica = new Hemodynamica();
        BeanUtils.copyProperties(hemodynamicaDTO, hemodynamica);
        hemodynamica.setObservedPatient(patient);
        patient.addHemodynamicParameter(hemodynamica);
        patientRepository.save(patient);
    }

    public PatientDTO addDoctor(Long doctorId, Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new PatientNotFoundException(patientId.toString()));
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new DoctorNotFoundException(doctorId.toString()));
        patient.addObserver(doctor);
        Patient saved = patientRepository.save(patient);
        PatientDTO responseDTO = new PatientDTO();
        BeanUtils.copyProperties(saved, responseDTO);
        DoctorDTO doctorDTO = new DoctorDTO();
        BeanUtils.copyProperties(doctor, doctorDTO);
        responseDTO.getDoctors().add(doctorDTO);
        responseDTO.setPatientId(saved.getId());
        return responseDTO;
    }

    public PatientDTO removeDoctor(Long doctorId, Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new PatientNotFoundException(patientId.toString()));
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new DoctorNotFoundException(doctorId.toString()));
        patient.removeObserver(doctor);
        Patient saved = patientRepository.save(patient);
        PatientDTO responseDTO = new PatientDTO();
        BeanUtils.copyProperties(saved, responseDTO);
        responseDTO.setPatientId(saved.getId());
        return responseDTO;
    }

    public void redAlert(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException(id.toString()));
        patient.redAlert();
    }

    public List<PatientDTO> getPatientsByDoctorId(Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new DoctorNotFoundException(doctorId.toString()));
        List<Patient> patients = doctor.getPatients();
        return DtoMapper.mapToPatientDTOs(patients);
    }

    public List<Hemodynamica> getCurrentHemodynamics(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException(id.toString()));
        return patient.getHemodynamics();
    }
}
