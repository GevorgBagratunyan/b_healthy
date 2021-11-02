package com.blueteam.tracker.controller;

import com.blueteam.tracker.dto.DoctorDTO;
import com.blueteam.tracker.service.DoctorService;
import com.blueteam.tracker.service.criteria.SearchCriteria;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping
    public ResponseEntity<DoctorDTO> create(@Valid @RequestBody DoctorDTO doctorDTO) {
        DoctorDTO responseDTO = doctorService.create(doctorDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<DoctorDTO> update(@Valid @RequestBody DoctorDTO doctorDTO,
                                        @PathVariable Long id) {
        DoctorDTO responseDTO = doctorService.update(doctorDTO, id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PutMapping("/patients/add")
    public ResponseEntity<DoctorDTO> addObservedPatient(@RequestParam Long doctorId,
                                                   @RequestParam Long patientId) {
        DoctorDTO responseDTO = doctorService.addPatient(doctorId, patientId);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PutMapping("/patients/remove")
    private ResponseEntity<DoctorDTO> removeObservedPatient(@RequestParam Long doctorId,
                                                       @RequestParam Long patientId) {
        DoctorDTO responseDTO = doctorService.removeObservedPatient(doctorId, patientId);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DoctorDTO> delete(@PathVariable Long id) {
        DoctorDTO responseDTO = doctorService.delete(id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
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

    @GetMapping("/patients/{id}")
    public ResponseEntity<Set<DoctorDTO>> getDoctorsByPatientId(@PathVariable Long id) {
        Set<DoctorDTO> doctorDTOs = doctorService.getDoctorsByPatientId(id);
        return new ResponseEntity<>(doctorDTOs, HttpStatus.OK);
    }
}
