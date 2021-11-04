package com.blueteam.appointment.controller;

import com.blueteam.appointment.service.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointment/doctor")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PutMapping("/accept-or-decline-an-appointment/{id}")
    public ResponseEntity<Void> acceptOrDeclineAnAppointment(
            @RequestParam Boolean isAccepted,
            @PathVariable Long appointmentId) {
        doctorService.acceptOrDeclineAnAppointment(isAccepted, appointmentId);
        return ResponseEntity.ok().build();
    }
}
