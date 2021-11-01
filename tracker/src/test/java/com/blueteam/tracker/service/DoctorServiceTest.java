package com.blueteam.tracker.service;

import com.blueteam.tracker.dto.DoctorDTO;
import com.blueteam.tracker.dto.PatientDTO;
import com.blueteam.tracker.entity.Doctor;
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

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

@SpringBootTest
class DoctorServiceTest {

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private DoctorService doctorService;

    @Test
    @DisplayName("Create Doctor")
    void createDoctorFromPassedDoctorDto() {
        DoctorDTO doctorDTO = new DoctorDTO(1L, "test@mail.com", "099202020");
        Doctor doctor = new Doctor();
        BeanUtils.copyProperties(doctorDTO, doctor);
        Mockito.when(doctorRepository.save(Mockito.any(Doctor.class))).thenReturn(doctor);
        DoctorDTO response = doctorService.create(doctorDTO);

        assertAll("All fields should be the same with passed dto",
                () -> assertEquals(1L, response.getObjId()),
                () -> assertEquals("test@mail.com", response.getEmail()),
                () -> assertEquals("099202020", response.getPhoneNumber()));
        Mockito.verify(doctorRepository, times(1)).save(doctor);
    }

    @Test
    @DisplayName("Get Doctor By")
    void getDoctorById_And_throwExceptionIfIdIsWrong() {
        Doctor doctor = new Doctor();
        doctor.setId(1L);
        doctor.setEmail("Test@mail.com");
        Mockito.when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor));
        DoctorDTO doctorDTO = doctorService.get(1L);

        assertEquals(doctorDTO.getDoctorId(), doctor.getId());
        assertEquals(doctorDTO.getEmail(), doctor.getEmail());
        assertThrows(DoctorNotFoundException.class, () -> doctorService.get(-1L));
    }

    @Test
    @DisplayName("Update doctor's info")
    void updateDoctor_And_VerifyWithReturnedDto() {
        DoctorDTO doctorDTO = new DoctorDTO(1L, "test@mail.com", "099202020");
        Doctor doctor = new Doctor();
        BeanUtils.copyProperties(doctorDTO, doctor);
        Mockito.when(doctorRepository.save(Mockito.any(Doctor.class))).thenReturn(doctor);
        DoctorDTO responseDto = doctorService.create(doctorDTO);

        assertAll("All fields should be the same with passed dto",
                () -> assertEquals(1L, responseDto.getObjId()),
                () -> assertEquals("test@mail.com", responseDto.getEmail()),
                () -> assertEquals("099202020", responseDto.getPhoneNumber()));
        Mockito.verify(doctorRepository, times(1)).save(doctor);
    }

    @Test
    @DisplayName("Get doctors limited by pageable")
    void getAllDoctorsUsingSearchCriteria_And_ThrowAllHandledExceptions() {
        List<Doctor> doctors = new ArrayList<>();
        Doctor doctor1 = new Doctor();
        doctor1.setId(1L);
        Doctor doctor2 = new Doctor();
        doctor2.setId(2L);
        Doctor doctor3 = new Doctor();
        doctor3.setId(3L);
        doctors.add(doctor1);
        doctors.add(doctor2);
        doctors.add(doctor3);
        Page<Doctor> page = new PageImpl<>(doctors);

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

        Mockito.when(doctorRepository.findAll(Mockito.any(Pageable.class))).thenReturn(page);

        Set<DoctorDTO> docs = doctorService.getAll(criteria);
        Mockito.verify(doctorRepository, times(1)).findAll(Mockito.any(Pageable.class));
        assertEquals(3, docs.size());
        assertAll("All wrong initialized criteria-s must throw handled exceptions",
                () -> assertThrows(SearchNullParametersException.class,
                        () -> doctorService.getAll(nullFieldsCriteria)),
                () -> assertThrows(SearchIllegalArgumentException.class,
                        () -> doctorService.getAll(illegalCriteria1)),
                () -> assertThrows(SearchIllegalArgumentException.class,
                        () -> doctorService.getAll(illegalCriteria2)),
                () -> assertThrows(SearchIllegalArgumentException.class,
                        () -> doctorService.getAll(illegalCriteria3)));
    }

    @Test
    @DisplayName("Add observed patient to doctor's list")
    void addObservedPatient_And_ReturnDto_And_ThrowExceptionsIfIdsIsWrong() {
        Doctor doctor = new Doctor();
        Patient patient = new Patient();
        patient.setEmail("patient@mail.com");
        doctor.setEmail("doctor@mail.com");
        doctor.getPatients().add(patient);

        Mockito.when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor));
        Mockito.when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
        Mockito.when(doctorRepository.save(doctor)).thenReturn(doctor);

        DoctorDTO doctorDTO = doctorService.addPatient(1L, 1L);
        assertEquals("doctor@mail.com", doctorDTO.getEmail());
        assertEquals("patient@mail.com", doctorDTO.getPatients().get(0).getEmail());
        assertThrows(PatientNotFoundException.class, () -> doctorService.addPatient(1L, 2L));
        assertThrows(DoctorNotFoundException.class, () -> doctorService.addPatient(2L, 1L));
    }

    @Test
    @DisplayName("Remove Observed Patient from observer doctor's list")
    void removeObservedPatientFromDoctorsPatientList() {
        Patient patient = new Patient();
        patient.setId(1L);
        Doctor doctor = new Doctor();
        doctor.setId(1L);
        Mockito.when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
        Mockito.when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor));
        Mockito.when(doctorRepository.save(Mockito.any(Doctor.class))).thenReturn(doctor);
        DoctorDTO doctorDTO = doctorService.removeObservedPatient(1L, 1L);

        assertEquals(doctorDTO.getDoctorId(), doctor.getId());
        assertThrows(PatientNotFoundException.class, () -> doctorService.removeObservedPatient(1L, -1L));
        assertThrows(DoctorNotFoundException.class, () -> doctorService.removeObservedPatient(-1L, 1L));
    }

    @Test
    @DisplayName("Get Observer doctors of patient by patient's id")
    void getObserverDoctorsByPatientId() {
        Patient patient = new Patient();
        patient.setId(1L);
        Doctor doctor1 = new Doctor();
        doctor1.setId(1L);
        Doctor doctor2 = new Doctor();
        doctor2.setId(2L);
        Doctor doctor3 = new Doctor();
        doctor3.setId(3L);
        Set<Doctor> observingDoctors = new HashSet<>(Arrays.asList(doctor1, doctor2, doctor3));
        patient.setDoctors(observingDoctors);

        Mockito.when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));

        Set<DoctorDTO> doctorDTOs = doctorService.getDoctorsByPatientId(1L);

        assertEquals(3, doctorDTOs.size());
        assertThrows(PatientNotFoundException.class, () -> doctorService.getDoctorsByPatientId(-1L));
    }
}