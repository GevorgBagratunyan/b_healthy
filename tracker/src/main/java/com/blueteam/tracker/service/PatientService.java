package com.blueteam.tracker.service;

import com.blueteam.tracker.dto.DoctorDTO;
import com.blueteam.tracker.dto.HemodynamicaDTO;
import com.blueteam.tracker.dto.PatientDTO;
import com.blueteam.tracker.entity.Hemodynamica;
import com.blueteam.tracker.entity.Patient;
import com.blueteam.tracker.entity.Doctor;
import com.blueteam.tracker.exception.doctor.DoctorNotFoundException;
import com.blueteam.tracker.exception.patient.PatientNotFoundException;
import com.blueteam.tracker.repository.HemodynamicaRepository;
import com.blueteam.tracker.repository.PatientRepository;
import com.blueteam.tracker.repository.DoctorRepository;
import com.blueteam.tracker.service.criteria.SearchCriteria;
import com.blueteam.tracker.service.crud.CRUD;
import com.blueteam.tracker.service.observer.ObservingService;
import com.blueteam.tracker.service.util.CriteriaValidator;
import com.blueteam.tracker.service.util.DtoMapper;
import com.blueteam.tracker.service.util.PageableCreator;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class PatientService implements CRUD<PatientDTO, Long> {

    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final HemodynamicaRepository hemodynamicaRepository;
    private final ObservingService observingService;

    public PatientService(DoctorRepository doctorRepository,
                          PatientRepository patientRepository,
                          HemodynamicaRepository hemodynamicaRepository,
                          ObservingService observingService) {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.hemodynamicaRepository = hemodynamicaRepository;
        this.observingService = observingService;
    }


    @Override
    @Transactional
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
    @Transactional
    public PatientDTO delete(Long id) {
        PatientDTO responseDTO = new PatientDTO();
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException(id.toString(), id));
        Set<Doctor> doctors = patient.getDoctors();
        for(Doctor d : doctors) {
            d.removePatient(patient);
        }
        BeanUtils.copyProperties(patient, responseDTO);
        responseDTO.setPatientId(patient.getId());
        patientRepository.deleteById(id);
        return responseDTO;
    }

    @Override
    public PatientDTO get(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException(id.toString(), id));
        PatientDTO responseDTO = new PatientDTO();
        BeanUtils.copyProperties(patient, responseDTO);
        responseDTO.setPatientId(patient.getId());
        DtoMapper.mapDoctorsToDoctorDTOs(patient, responseDTO);
        return responseDTO;
    }

    @Override
    @Transactional
    public PatientDTO update(PatientDTO patientDTO, Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException(id.toString(), id));
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

    @Transactional
    public void trackHemodynamicParams(HemodynamicaDTO hemodynamicaDTO, Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException(id.toString(), id));
        Hemodynamica hemodynamica = new Hemodynamica();
        BeanUtils.copyProperties(hemodynamicaDTO, hemodynamica);
        hemodynamica.setObservedPatient(patient);
        hemodynamicaRepository.save(hemodynamica);
        if(patient.getTracking()) {
            observingService.observe(patient.getObjId(), patient.getName(),
                    patient.getPhoneNumber(), hemodynamica,
                    patient.getDoctors(), patient.getHemodynamics());
        }
        patientRepository.save(patient);
    }

    @Transactional
    public PatientDTO addDoctor(Long doctorId, Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new PatientNotFoundException(patientId.toString(), patientId));
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new DoctorNotFoundException(doctorId.toString(), doctorId));
        patient.addObserver(doctor);
        doctor.addPatient(patient);
        doctorRepository.save(doctor);
        Patient saved = patientRepository.save(patient);
        PatientDTO responseDTO = new PatientDTO();
        BeanUtils.copyProperties(saved, responseDTO);
        DoctorDTO doctorDTO = new DoctorDTO();
        BeanUtils.copyProperties(doctor, doctorDTO);
        doctorDTO.setDoctorId(doctor.getId());
        responseDTO.getDoctors().add(doctorDTO);
        responseDTO.setPatientId(saved.getId());
        return responseDTO;
    }

    @Transactional
    public PatientDTO removeDoctor(Long doctorId, Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new PatientNotFoundException(patientId.toString(), patientId));
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new DoctorNotFoundException(doctorId.toString(), doctorId));
        patient.removeObserver(doctor);
        doctor.removePatient(patient);
        doctorRepository.save(doctor);
        Patient saved = patientRepository.save(patient);
        PatientDTO responseDTO = new PatientDTO();
        BeanUtils.copyProperties(saved, responseDTO);
        responseDTO.setPatientId(saved.getId());
        return responseDTO;
    }

    public List<PatientDTO> getPatientsByDoctorId(Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new DoctorNotFoundException(doctorId.toString(), doctorId));
        List<Patient> patients = doctor.getPatients();
        return DtoMapper.mapToPatientDTOs(patients);
    }

    public List<HemodynamicaDTO> getCurrentHemodynamics(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException(id.toString(), id));
        List<Hemodynamica> hemodynamics = patient.getHemodynamics();
        List<HemodynamicaDTO> hemodynamicaDTOs = new ArrayList<>();
        for(Hemodynamica hemodynamica : hemodynamics) {
            HemodynamicaDTO hemodynamicaDTO = new HemodynamicaDTO();
            BeanUtils.copyProperties(hemodynamica, hemodynamicaDTO);
            hemodynamicaDTOs.add(hemodynamicaDTO);
        }
        return hemodynamicaDTOs;
    }

    @Transactional
    public PatientDTO setTrackingOnOrOff(Long id, Boolean isTracking) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException(id.toString(), id));
        patient.setTracking(isTracking);
        Patient saved = patientRepository.save(patient);
        PatientDTO responseDTO = new PatientDTO();
        BeanUtils.copyProperties(saved, responseDTO);
        responseDTO.setPatientId(saved.getId());
        return responseDTO;
    }
}
