package com.blueteam.tracker.controller;

import com.blueteam.tracker.dto.HemodynamicaDTO;
import com.blueteam.tracker.dto.PatientDTO;
import com.blueteam.tracker.entity.Doctor;
import com.blueteam.tracker.entity.Patient;
import com.blueteam.tracker.exception.patient.PatientNotFoundException;
import com.blueteam.tracker.repository.PatientRepository;
import com.blueteam.tracker.util.SecurityUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username="admin",roles={"ADMIN"})
class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private static PatientRepository patientRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void trackHemodynamica() throws Exception {
        Patient patient = new Patient();
        patient.setEmail("patient@mail.com");
        patient.setPhoneNumber("+37499123456");
        patient.setName("Patient");
        HemodynamicaDTO hemodynamicaDTO = new HemodynamicaDTO();
        hemodynamicaDTO.setSaturation(80);
        hemodynamicaDTO.setHeartRate(150);
        patientRepository.save(patient);

        mockMvc.perform(MockMvcRequestBuilders.put("/tracker/patient/track/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + SecurityUtils.getAdminJWT())
                .content(objectMapper.writeValueAsString(hemodynamicaDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.heartRate").exists());
    }

    @Test
    void setTrackingOnOrOff() {
    }

    @Test
    void addObservingDoctor() {
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

        mockMvc.perform(MockMvcRequestBuilders.post("/tracker")
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