package com.blueteam.appointment.controller;

import com.blueteam.appointment.dto.DoctorDTO;
import com.blueteam.appointment.service.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/appointment/doctor")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PutMapping("/accept-or-decline/{id}")
    public ResponseEntity<Void> acceptOrDeclineAnAppointment(
            @RequestParam Boolean isAccepted,
            @PathVariable(name = "id") Long appointmentId) {
        doctorService.acceptOrDeclineAnAppointment(isAccepted, appointmentId);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<DoctorDTO> create(
            @Valid @RequestBody DoctorDTO doctorDTO) {
        DoctorDTO dto = doctorService.create(doctorDTO);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DoctorDTO> delete(@PathVariable Long id) {
        DoctorDTO dto = doctorService.delete(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/{id}}")
    public ResponseEntity<DoctorDTO> get(@PathVariable Long id) {
        DoctorDTO dto = doctorService.get(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorDTO> update(@PathVariable Long id,
                                            @Valid @RequestBody DoctorDTO doctorDTO) {
        DoctorDTO dto = doctorService.update(doctorDTO, id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
