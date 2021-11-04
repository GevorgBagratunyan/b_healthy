package com.blueteam.appointment.controller;

import com.blueteam.appointment.dto.AppointmentDTO;
import com.blueteam.appointment.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointment/patient")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/make-appointment")
    public ResponseEntity<Void> makeAnAppointment(
            @RequestBody AppointmentDTO appointmentDTO) {
        patientService.makeAnAppointment(appointmentDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/cancel/{id}")
    public ResponseEntity<Void> cancelAnAppointment(
            @PathVariable(name = "id") Long appointmentId) {
        patientService.cancelAnAppointment(appointmentId);
        return ResponseEntity.ok().build();
    }
}
