package com.blueteam.tracker.controller;

import com.blueteam.tracker.dto.HemodynamicaDTO;
import com.blueteam.tracker.dto.PatientDTO;
import com.blueteam.tracker.entity.Doctor;
import com.blueteam.tracker.entity.Hemodynamica;
import com.blueteam.tracker.entity.Patient;
import com.blueteam.tracker.repository.DoctorRepository;
import com.blueteam.tracker.repository.PatientRepository;
import com.blueteam.tracker.util.SecurityUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "admin", roles = {"ADMIN"})
class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @BeforeEach
    public void init() {
        Patient patient = new Patient();
        patient.setEmail("patient@mail.com");
        patient.setPhoneNumber("0037499123456");
        patient.setName("Patient");
        patient.setObjId(14L);
        patient.setTracking(false);
        patientRepository.saveAndFlush(patient);

        Doctor doctor = new Doctor();
        doctor.setEmail("Doctor@mail.com");
        doctor.setPhoneNumber("0037499555555");
        doctor.setName("Doctor");
        doctor.setObjId(15L);
        doctorRepository.save(doctor);

    }

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void trackHemodynamica() throws Exception {
        HemodynamicaDTO hemodynamicaDTO = new HemodynamicaDTO();
        hemodynamicaDTO.setSaturation(95);
        hemodynamicaDTO.setHeartRate(80);

        mockMvc.perform(MockMvcRequestBuilders.put("/tracker/patient/track/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + SecurityUtils.getAdminJWT())
                        .content(objectMapper.writeValueAsString(hemodynamicaDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void setTrackingOnOrOff() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.put("/tracker/patient/set-tracking")
                        .param("id", String.valueOf(1L))
                        .param("isTracking", String.valueOf(false))
                        .header("Authorization", "Bearer " + SecurityUtils.getAdminJWT()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.patientId").exists());
    }

    @Test
    void addObservingDoctor() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.put("/tracker/patient/doctors/add")
                        .param("doctorId", String.valueOf(2L))
                        .param("patientId", String.valueOf(1L))
                        .header("Authorization", "Bearer " + SecurityUtils.getAdminJWT()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.patientId").exists());
    }

    @Test
    void removeObservingDoctor() {
    }

    @Test
    void update() {
    }

    @Test
    void getAll() {
    }

    @Test
    void getObservedPatientsByDoctorId() {
    }

    @Test
    void get() {
    }

    @Test
    void getCurrentHemodynamica() {
    }

    @Test
    void create() throws Exception {
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setEmail("Patient@mail.com");
        patientDTO.setPhoneNumber("099202020");
        patientDTO.setObjId(14L);

        mockMvc.perform(MockMvcRequestBuilders.post("/tracker/patient")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + SecurityUtils.getAdminJWT())
                        .content(objectMapper.writeValueAsString(patientDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.patientId").exists());
    }

    @Test
    void delete() {
    }
}