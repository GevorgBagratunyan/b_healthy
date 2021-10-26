package com.blueteam.tracker.service.util;

import com.blueteam.tracker.dto.DoctorDTO;
import com.blueteam.tracker.dto.PatientDTO;
import com.blueteam.tracker.entity.Patient;
import com.blueteam.tracker.entity.Doctor;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DtoMapper {

    private DtoMapper() {

    }

    public static List<PatientDTO> mapToPatientDTOs(List<Patient> patients) {
        List<PatientDTO> patientDTOs = new ArrayList<>();

        for (Patient patient : patients) {
            PatientDTO patientDTO = new PatientDTO();
            BeanUtils.copyProperties(patient, patientDTO);
            patientDTO.setPatientId(patient.getId());
            mapDoctorsToDoctorDTOs(patient,patientDTO);
            patientDTOs.add(patientDTO);
        }

        return patientDTOs;
    }

    public static void mapDoctorsToDoctorDTOs(Patient patient, PatientDTO patientDTO) {
        List<DoctorDTO> doctorDTOs = new ArrayList<>();
        Set<Doctor> doctors = patient.getDoctors();
        for(Doctor doctor : doctors) {
            DoctorDTO doctorDTO = new DoctorDTO();
            BeanUtils.copyProperties(doctor, doctorDTO);
            doctorDTO.setDoctorId(doctor.getId());
            doctorDTOs.add(doctorDTO);
        }
        patientDTO.setDoctors(doctorDTOs);
    }

    public static Set<DoctorDTO> mapToDoctorDTOs(Set<Doctor> doctors) {
        Set<DoctorDTO> doctorDTOs = new HashSet<>();

        for (Doctor doctor : doctors) {
            DoctorDTO doctorDTO = new DoctorDTO();
            BeanUtils.copyProperties(doctor, doctorDTO);
            doctorDTO.setDoctorId(doctor.getId());
            mapPatientsToPatientDTOs(doctor, doctorDTO);
            doctorDTOs.add(doctorDTO);
        }
        return doctorDTOs;
    }

    public static void mapPatientsToPatientDTOs(Doctor doctor, DoctorDTO doctorDTO) {
        List<PatientDTO> patientDTOs = new ArrayList<>();
        List<Patient> patients = doctor.getPatients();
        for(Patient patient : patients) {
            PatientDTO patientDTO = new PatientDTO();
            BeanUtils.copyProperties(patient, patientDTO);
            patientDTO.setPatientId(patient.getId());
            patientDTOs.add(patientDTO);
        }
        doctorDTO.setPatients(patientDTOs);
    }
}
