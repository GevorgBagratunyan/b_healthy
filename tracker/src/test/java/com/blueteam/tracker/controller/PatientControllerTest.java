package com.blueteam.tracker.controller;

import com.blueteam.tracker.dto.HemodynamicaDTO;
import com.blueteam.tracker.dto.PatientDTO;
import com.blueteam.tracker.entity.Doctor;
import com.blueteam.tracker.entity.Patient;
import com.blueteam.tracker.repository.DoctorRepository;
import com.blueteam.tracker.repository.PatientRepository;
import com.blueteam.tracker.service.DoctorService;
import com.blueteam.tracker.service.PatientService;
import com.blueteam.tracker.service.criteria.SearchCriteria;
import com.blueteam.tracker.util.SecurityUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

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

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void init() {
        Patient patient = new Patient();
        patient.setEmail("patient@mail.com");
        patient.setPhoneNumber("0037499123456");
        patient.setName("Patient");
        patient.setObjId(14L);
        patient.setTracking(false);

        Doctor doctor = new Doctor();
        doctor.setEmail("Doctor@mail.com");
        doctor.setPhoneNumber("0037499555555");
        doctor.setName("Doctor");
        doctor.setObjId(15L);

        patient.addObserver(doctor);
        doctor.getPatients().add(patient);
        patientRepository.save(patient); //id 1L
        doctorRepository.save(doctor); // id 2L
    }

    @AfterEach
    public void drop() throws Exception {
        // Disable integrity constraint
        //  List all tables in the (default) PUBLIC schema
        //  Truncate all tables
        //  List all sequences in the (default) PUBLIC schema
        //  Reset all sequences
        //  Re-enable the constraints.
        Connection c = DriverManager.getConnection("jdbc:h2:mem:med_app", "postgres", "123456");
        Statement s = c.createStatement();

        // Disable FK
        s.execute("SET REFERENTIAL_INTEGRITY = FALSE");

        // Find all tables and truncate them
        Set<String> tables = new HashSet<>();
        ResultSet rs = s.executeQuery("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES  where TABLE_SCHEMA='PUBLIC'");
        while (rs.next()) {
            tables.add(rs.getString(1));
        }
        rs.close();
        for (String table : tables) {
            s.executeUpdate("TRUNCATE TABLE " + table);
        }

        // Idem for sequences
        Set<String> sequences = new HashSet<>();
        rs = s.executeQuery("SELECT SEQUENCE_NAME FROM INFORMATION_SCHEMA.SEQUENCES WHERE SEQUENCE_SCHEMA='PUBLIC'");
        while (rs.next()) {
            sequences.add(rs.getString(1));
        }
        rs.close();
        for (String seq : sequences) {
            s.executeUpdate("ALTER SEQUENCE " + seq + " RESTART WITH 1");
        }

        // Enable FK
        s.execute("SET REFERENTIAL_INTEGRITY = TRUE");
        s.close();
        c.close();

    }

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
    void removeObservingDoctor() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/tracker/patient/doctors/remove")
                        .param("doctorId", String.valueOf(2L))
                        .param("patientId", String.valueOf(1L))
                        .header("Authorization", "Bearer " + SecurityUtils.getAdminJWT()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.patientId").exists());
    }

    @Test
    void update() throws Exception {
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setEmail("Patient@mail.com");
        patientDTO.setPhoneNumber("099202020");
        patientDTO.setObjId(14L);
        mockMvc.perform(MockMvcRequestBuilders.put("/tracker/patient/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + SecurityUtils.getAdminJWT())
                        .content(objectMapper.writeValueAsString(patientDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.patientId").exists());
    }

    @Test
    void getAll() throws Exception {
        SearchCriteria criteria = new SearchCriteria();
        criteria.setSort("ASC");
        criteria.setLimit(3);
        criteria.setOffset(0);
        criteria.setOrderByFieldName("email");
        mockMvc.perform(MockMvcRequestBuilders.get("/tracker/patient")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + SecurityUtils.getAdminJWT())
                        .content(objectMapper.writeValueAsString(criteria)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.[*].name").value(Matchers.hasItem("Patient")))
                .andExpect(jsonPath("$.[*]").isArray());
    }

    @Test
    void get() throws Exception {
        Long id = 1L;
        mockMvc.perform(MockMvcRequestBuilders.get("/tracker/patient/{id}", id)
                        .header("Authorization", "Bearer " + SecurityUtils.getAdminJWT()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.patientId").value(id))
                .andExpect(jsonPath("$.name").value("Patient"));
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
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/tracker/patient/{id}", 1L)
                        .header("Authorization", "Bearer " + SecurityUtils.getAdminJWT()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.patientId").value(1L))
                .andExpect(jsonPath("$.name").value("Patient"));
    }
}