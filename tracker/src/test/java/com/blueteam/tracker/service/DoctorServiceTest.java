package com.blueteam.tracker.service;

import com.blueteam.tracker.dto.DoctorDTO;
import com.blueteam.tracker.entity.Doctor;
import com.blueteam.tracker.exception.doctor.DoctorNotFoundException;
import com.blueteam.tracker.repository.DoctorRepository;
import com.blueteam.tracker.service.criteria.SearchCriteria;
import com.blueteam.tracker.service.util.DtoMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DoctorServiceTest {

    @Mock
    private DoctorRepository repository;

    @InjectMocks
    private DoctorService service;

    @Test
    @DisplayName("Save Doctor to DB")
    void createDoctorFromPassedDoctorDto() {
        DoctorDTO doctorDTORequest = new DoctorDTO();
        doctorDTORequest.setEmail("Test@mail.com");

        Doctor doctorToSave = new Doctor();
        doctorToSave.setId(1L);
        BeanUtils.copyProperties(doctorDTORequest, doctorToSave);
        Doctor returned = new Doctor();
        returned.setEmail("Test@mail.com");
        returned.setId(1L);

        Mockito.when(repository.save(doctorToSave)).thenReturn(returned);

        repository.save(doctorToSave);

        assertEquals(doctorToSave.getEmail(), doctorDTORequest.getEmail());
    }

    @Test
    @DisplayName("Get Doctor By id and throw an exception if id is wrong")
    void getDoctorById() {
        Doctor doctor = new Doctor();
        doctor.setEmail("test@email.com");
        DoctorDTO doctorDTO = new DoctorDTO();

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(doctor));
        Mockito.when(repository.findById(2L)).thenThrow(new DoctorNotFoundException("2L"));

        Optional<Doctor> expectedOpt = repository.findById(1L);
        assertTrue(expectedOpt.isPresent());
        Doctor expected = expectedOpt.get();
        BeanUtils.copyProperties(expected, doctorDTO);

        assertEquals("test@email.com", expected.getEmail());
        assertEquals("test@email.com", doctorDTO.getEmail());
        assertThrows(DoctorNotFoundException.class, () -> service.get(-1L));
    }

    @Test
    void update() {
        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setEmail("test@email.com");
        Doctor doctorToUpdate = new Doctor();
        BeanUtils.copyProperties(doctorDTO, doctorToUpdate);

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(doctorToUpdate));

        assertEquals("test@email.com", doctorToUpdate.getEmail());
        assertEquals(Optional.of(doctorToUpdate), repository.findById(1L));
        assertThrows(DoctorNotFoundException.class, () -> service.get(-1L));
    }

    @Test
    void getAll() {

        Doctor doctor1 = new Doctor();
        Doctor doctor2 = new Doctor();
        Doctor doctor3 = new Doctor();
        List<Doctor> doctors = new ArrayList<>(Arrays.asList(doctor1, doctor2, doctor3));

        SearchCriteria criteria = new SearchCriteria();
        criteria.setLimit(3);
        criteria.setOffset(0);
        criteria.setSort("ASC");

        Mockito.when(repository.findAll()).thenReturn(doctors);

        Set<Doctor> docs = new HashSet<>(repository.findAll());
        Set<DoctorDTO> doctorDTOs = DtoMapper.mapToDoctorDTOs(docs);
        assertEquals(doctorDTOs, service.getAll(criteria));

    }

    @Test
    void addPatient() {
    }

    @Test
    void removeObservedPatient() {
    }

    @Test
    void getDoctorsByPatientId() {
    }
}