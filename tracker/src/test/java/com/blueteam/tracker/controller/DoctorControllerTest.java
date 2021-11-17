package com.blueteam.tracker.controller;

import com.blueteam.tracker.dto.DoctorDTO;
import com.blueteam.tracker.dto.PatientDTO;
import com.blueteam.tracker.entity.Doctor;
import com.blueteam.tracker.entity.Patient;
import com.blueteam.tracker.repository.DoctorRepository;
import com.blueteam.tracker.repository.PatientRepository;
import com.blueteam.tracker.service.criteria.SearchCriteria;
import com.blueteam.tracker.util.SecurityUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "admin", roles = {"ADMIN"})
class DoctorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void init() {
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
    void drop() throws Exception{
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
    @DisplayName("Create Doctor")
    void createDoctor() throws Exception {
        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setEmail("Doctor@mail.com");
        doctorDTO.setPhoneNumber("099202020");
        doctorDTO.setObjId(14L);
        mockMvc.perform(MockMvcRequestBuilders.post("/tracker/doctor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + SecurityUtils.getAdminJWT())
                        .content(objectMapper.writeValueAsString(doctorDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.doctorId").exists());
    }

    @Test
    @DisplayName("Update Doctor")
    void update() throws Exception{
        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setEmail("Doctor@mail.com");
        doctorDTO.setPhoneNumber("099202020");
        doctorDTO.setObjId(14L);
        mockMvc.perform(MockMvcRequestBuilders.post("/tracker/doctor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + SecurityUtils.getAdminJWT())
                        .content(objectMapper.writeValueAsString(doctorDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.doctorId").exists());
    }

    @Test
    @DisplayName("Add Observed Patient to Doctor's list of observed patients")
    void addObservedPatient() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.put("/tracker/doctor/patients/add")
                        .param("doctorId", String.valueOf(2L))
                        .param("patientId", String.valueOf(1L))
                        .header("Authorization", "Bearer " + SecurityUtils.getAdminJWT()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.doctorId").exists());
    }

    @Test
    @DisplayName("Delete Doctor")
    void deleteDoctor() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/tracker/doctor/{id}", 2L)
                        .header("Authorization", "Bearer " + SecurityUtils.getAdminJWT()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.doctorId").value(2L))
                .andExpect(jsonPath("$.name").value("Doctor"));
    }

    @Test
    @DisplayName("Get Doctor by id")
    void getDoctorById() throws Exception {
        Long id = 2L;
        mockMvc.perform(MockMvcRequestBuilders.get("/tracker/doctor/{id}", id)
                        .header("Authorization", "Bearer " + SecurityUtils.getAdminJWT()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.doctorId").value(id))
                .andExpect(jsonPath("$.name").value("Doctor"));
    }

    @Test
    @DisplayName("Get all Doctors using search criteria")
    void getAllDoctorsBySearchCriteria() throws Exception{
        SearchCriteria criteria = new SearchCriteria();
        criteria.setSort("ASC");
        criteria.setLimit(3);
        criteria.setOffset(0);
        criteria.setOrderByFieldName("email");
        mockMvc.perform(MockMvcRequestBuilders.get("/tracker/doctor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + SecurityUtils.getAdminJWT())
                        .content(objectMapper.writeValueAsString(criteria)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.[*].name").value(Matchers.hasItem("Doctor")))
                .andExpect(jsonPath("$.[*]").isArray());
    }

    @Test
    @DisplayName("Get observing Doctors of patient by patient's id")
    void getDoctorsByPatientId() throws Exception{
        Long id = 1L;
        mockMvc.perform(MockMvcRequestBuilders.get("/tracker/doctor/patients/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + SecurityUtils.getAdminJWT()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.[*].name").value(Matchers.hasItem("Doctor")))
                .andExpect(jsonPath("$.[*]").isArray());
    }
}