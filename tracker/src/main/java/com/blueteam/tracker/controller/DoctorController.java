package com.blueteam.tracker.controller;

import com.blueteam.tracker.dto.DoctorDTO;
import com.blueteam.tracker.service.DoctorService;
import com.blueteam.tracker.service.criteria.SearchCriteria;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody DoctorDTO doctorDTO) {
        doctorService.create(doctorDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public  ResponseEntity<Void> update(@RequestBody DoctorDTO doctorDTO,
                                        @PathVariable Long id) {
        doctorService.update(doctorDTO, id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/patients/add")
    public ResponseEntity<Void> addObservedPatient(@RequestParam Long doctorId,
                                                   @RequestParam Long patientId) {
        doctorService.addPatient(doctorId, patientId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/patients/remove")
    private ResponseEntity<Void> removeObservedPatient(@RequestParam Long doctorId,
                                                       @RequestParam Long patientId) {
        doctorService.removeObservedPatient(doctorId, patientId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        doctorService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDTO> get(@PathVariable Long id) {
        DoctorDTO doctorDTO = doctorService.get(id);
        return new ResponseEntity<>(doctorDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Set<DoctorDTO>> getAll(@RequestBody SearchCriteria criteria) {
        Set<DoctorDTO> doctorDTOs = doctorService.getAll(criteria);
        return new ResponseEntity<>(doctorDTOs, HttpStatus.OK);
    }

    @GetMapping("patients/{id}")
    public ResponseEntity<Set<DoctorDTO>> getDoctorsByPatientId(@PathVariable Long id) {
        Set<DoctorDTO> doctorDTOs = doctorService.getDoctorsByPatientId(id);
        return new ResponseEntity<>(doctorDTOs, HttpStatus.OK);
    }
}
