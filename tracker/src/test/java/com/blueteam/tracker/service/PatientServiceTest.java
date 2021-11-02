package com.blueteam.tracker.service;

import com.blueteam.tracker.dto.HemodynamicaDTO;
import com.blueteam.tracker.dto.PatientDTO;
import com.blueteam.tracker.entity.Doctor;
import com.blueteam.tracker.entity.Hemodynamica;
import com.blueteam.tracker.entity.Patient;
import com.blueteam.tracker.exception.doctor.DoctorNotFoundException;
import com.blueteam.tracker.exception.patient.PatientNotFoundException;
import com.blueteam.tracker.exception.search.SearchIllegalArgumentException;
import com.blueteam.tracker.exception.search.SearchNullParametersException;
import com.blueteam.tracker.repository.DoctorRepository;
import com.blueteam.tracker.repository.PatientRepository;
import com.blueteam.tracker.service.criteria.SearchCriteria;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

@SpringBootTest
class PatientServiceTest {

    //Used to simulate the behavior of a real object
    @Mock
    private PatientRepository patientRepository;

    @Mock
    private DoctorRepository doctorRepository;

    //Creates an instance of the class and injects the mock
    //created with the @Mock annotation into this instance
    @InjectMocks
    private PatientService patientService;

    @Test
    @DisplayName("Create Patient and validate returned DTO")
    void createPatient_And_VerifyWithReturnedDto() {
        PatientDTO patientDTO = new PatientDTO(1L, "test@mail.com", "099202020");
        Patient patient = new Patient();
        BeanUtils.copyProperties(patientDTO, patient);
        Mockito.when(patientRepository.save(Mockito.any(Patient.class))).thenReturn(patient);
        PatientDTO response = patientService.create(patientDTO);

        assertAll("All fields should be the same with passed dto",
                () -> assertEquals(1L, response.getObjId()),
                () -> assertEquals("test@mail.com", response.getEmail()),
                () -> assertEquals("099202020", response.getPhoneNumber()));
        Mockito.verify(patientRepository, times(1)).save(patient);
    }

    @Test
    @DisplayName("Delete Patient by id and return deleted DTO")
    void deletePatientById_And_ReturnDeletedDto() {
        Patient patient = new Patient();
        patient.setId(1L);
        Mockito.when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
        PatientDTO patientDTO = patientService.delete(1L);

        assertEquals(1L, patientDTO.getPatientId());
        assertThrows(PatientNotFoundException.class, () -> patientService.delete(-1L));
    }

    @Test
    @DisplayName("Get Patient by id")
    void getPatientById_And_throwExceptionIfIdIsWrong() {
        Patient patient = new Patient();
        patient.setId(1L);
        patient.setEmail("Test@mail.com");
        Mockito.when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
        PatientDTO patientDTO = patientService.get(1L);

        assertEquals(patientDTO.getPatientId(), patient.getId());
        assertEquals(patientDTO.getEmail(), patient.getEmail());
        assertThrows(PatientNotFoundException.class, () -> patientService.get(-1L));
    }

    @Test
    @DisplayName("Update patient's info")
    void updateDoctor_And_VerifyWithReturnedDto() {
        PatientDTO patientDTO = new PatientDTO(1L, "test@mail.com", "099202020");
        Patient patient = new Patient();
        BeanUtils.copyProperties(patientDTO, patient);
        Mockito.when(patientRepository.save(Mockito.any(Patient.class))).thenReturn(patient);
        PatientDTO responseDto = patientService.create(patientDTO);

        assertAll("All fields should be the same with passed dto",
                () -> assertEquals(1L, responseDto.getObjId()),
                () -> assertEquals("test@mail.com", responseDto.getEmail()),
                () -> assertEquals("099202020", responseDto.getPhoneNumber()));
        Mockito.verify(patientRepository, times(1)).save(patient);
    }

    @Test
    @DisplayName("Get patients limited by pageable")
    void getAllPatientsUsingSearchCriteria_And_ThrowAllHandledExceptions() {
        SearchCriteria criteria = new SearchCriteria();
        criteria.setSort("ASC");
        criteria.setLimit(3);
        criteria.setOffset(1);
        criteria.setOrderByFieldName("email");

        SearchCriteria illegalCriteria1 = new SearchCriteria();
        illegalCriteria1.setLimit(3);
        illegalCriteria1.setOffset(1);
        illegalCriteria1.setSort("WRONG VALUE");

        SearchCriteria illegalCriteria2 = new SearchCriteria();
        illegalCriteria2.setLimit(-3);
        illegalCriteria2.setOffset(-1);

        SearchCriteria illegalCriteria3 = new SearchCriteria();
        illegalCriteria3.setLimit(3);
        illegalCriteria3.setOffset(1);
        illegalCriteria3.setOrderByFieldName("WRONG VALUE");

        SearchCriteria nullFieldsCriteria = new SearchCriteria();

        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient());
        patients.add(new Patient());
        patients.add(new Patient());
        Page<Patient> page = new PageImpl<>(patients);
        //This is an alternative way
//        given(patientRepository.findAll(any(Pageable.class))).willReturn(page);
        Mockito.when(patientRepository.findAll(Mockito.any(Pageable.class))).thenReturn(page);

        List<PatientDTO> responseList = patientService.getAll(criteria);
        assertEquals(3, responseList.size());
        Mockito.verify(patientRepository, times(1)).findAll(Mockito.any(Pageable.class));
        assertAll("All wrong initialized criteria-s must throw handled exceptions",
                () -> assertThrows(SearchNullParametersException.class,
                        () -> patientService.getAll(nullFieldsCriteria)),
                () -> assertThrows(SearchIllegalArgumentException.class,
                        () -> patientService.getAll(illegalCriteria1)),
                () -> assertThrows(SearchIllegalArgumentException.class,
                        () -> patientService.getAll(illegalCriteria2)),
                () -> assertThrows(SearchIllegalArgumentException.class,
                        () -> patientService.getAll(illegalCriteria3)));
    }

    @Test
    @DisplayName("Track Patient")
    void trackHemodynamicParams() {
        HemodynamicaDTO hemodynamica = new HemodynamicaDTO();
        Patient patient = new Patient();
        Mockito.when(patientRepository.save(Mockito.any(Patient.class))).thenReturn(patient);

        assertThrows(PatientNotFoundException.class, () -> patientService.trackHemodynamicParams(hemodynamica, -1L));
    }

    @Test
    @DisplayName("Add Observer Doctor to observed patient's list")
    void addObserverDoctorToPatientDoctorsList() {
        Doctor doctor = new Doctor();
        Patient patient = new Patient();
        patient.setEmail("patient@mail.com");
        doctor.setEmail("doctor@mail.com");
        patient.getDoctors().add(doctor);
        Mockito.when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
        Mockito.when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor));
        Mockito.when(patientRepository.save(Mockito.any(Patient.class))).thenReturn(patient);
        PatientDTO patientDTO = patientService.addDoctor(1L, 1L);

        assertEquals("patient@mail.com", patientDTO.getEmail());
        assertEquals("doctor@mail.com", patientDTO.getDoctors().get(0).getEmail());
        assertThrows(PatientNotFoundException.class, () -> patientService.addDoctor(1L, 2L));
        assertThrows(DoctorNotFoundException.class, () -> patientService.addDoctor(2L, 1L));
    }

    @Test
    @DisplayName("Remove Observer Doctor from observed patient's list")
    void removeObserverDoctorFromPatientsDoctorList() {
        Patient patient = new Patient();
        patient.setId(1L);
        Doctor doctor = new Doctor();
        doctor.setId(1L);
        Mockito.when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
        Mockito.when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor));
        Mockito.when(patientRepository.save(Mockito.any(Patient.class))).thenReturn(patient);
        PatientDTO patientDTO = patientService.removeDoctor(1L, 1L);

        assertEquals(patientDTO.getPatientId(), patient.getId());
        assertThrows(PatientNotFoundException.class, () -> patientService.removeDoctor(1L, -1L));
        assertThrows(DoctorNotFoundException.class, () -> patientService.removeDoctor(-1L, 1L));
    }

    @Test
    @DisplayName("Get Observed patients of doctor by doctor's id")
    void getObservedPatientsByDoctorId() {
        Doctor doctor = new Doctor();
        doctor.setId(1L);
        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient());
        patients.add(new Patient());
        patients.add(new Patient());
        doctor.setPatients(patients);
        Mockito.when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor));

        List<PatientDTO> patientDTOs = patientService.getPatientsByDoctorId(1L);

        assertEquals(3, patientDTOs.size());
        assertThrows(DoctorNotFoundException.class, () -> patientService.getPatientsByDoctorId(-1L));
    }

    @Test
    @DisplayName("Get Hemodynamic parameters of patient by patient id")
    void getCurrentHemodynamicParametersOfPatient() {
        Patient patient = new Patient();
        patient.setId(1L);
        patient.addHemodynamicParameter(new Hemodynamica());
        patient.addHemodynamicParameter(new Hemodynamica());
        patient.addHemodynamicParameter(new Hemodynamica());
        Mockito.when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
        List<HemodynamicaDTO> hemodynamics = patientService.getCurrentHemodynamics(1L);

        assertEquals(3, hemodynamics.size());
    }
}