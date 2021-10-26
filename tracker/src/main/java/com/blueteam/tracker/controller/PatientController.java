package com.blueteam.tracker.controller;

import com.blueteam.tracker.dto.HemodynamicaDTO;
import com.blueteam.tracker.dto.PatientDTO;
import com.blueteam.tracker.entity.Hemodynamica;
import com.blueteam.tracker.service.PatientService;
import com.blueteam.tracker.service.criteria.SearchCriteria;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PutMapping("/track/{id}")
    public ResponseEntity<Void> trackHemodynamica(@Valid @RequestBody HemodynamicaDTO hemodynamica,
                                                  @NotNull @PathVariable Long id) {
        patientService.trackHemodynamicParams(hemodynamica, id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/doctors/add")
    public ResponseEntity<PatientDTO> addObservingDoctor(@RequestParam Long doctorId,
                                                   @RequestParam Long patientId) {
        PatientDTO responseDTO = patientService.addDoctor(doctorId, patientId);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PutMapping("/doctors/remove")
    public ResponseEntity<PatientDTO> removeObservingDoctor(@RequestParam Long doctorId,
                                                      @RequestParam Long patientId) {
        PatientDTO responseDTO = patientService.removeDoctor(doctorId, patientId);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PutMapping("/alert/{id}")
    public ResponseEntity<Void> redAlert(@PathVariable Long id){
        patientService.redAlert(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public  ResponseEntity<PatientDTO> update(@Valid @RequestBody PatientDTO patientDTO,
                                        @PathVariable Long id) {
        PatientDTO responseDTO = patientService.update(patientDTO, id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PatientDTO>> getAll(@RequestBody SearchCriteria criteria) {
        List<PatientDTO> patientDTOs = patientService.getAll(criteria);
        return new ResponseEntity<>(patientDTOs, HttpStatus.OK);
    }

    @GetMapping("/doctors/{id}")
    public ResponseEntity<List<PatientDTO>> getObservedPatientsByDoctorId(@PathVariable Long id) {
        List<PatientDTO> patientDTOs = patientService.getPatientsByDoctorId(id);
        return new ResponseEntity<>(patientDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> get(@PathVariable Long id) {
        PatientDTO patient = patientService.get(id);
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @GetMapping("/current-hemodynamics/{id}")
    public ResponseEntity<List<Hemodynamica>> getCurrentHemodynamica(@PathVariable Long id) {
        List<Hemodynamica> hemodynamics = patientService.getCurrentHemodynamics(id);
        return new ResponseEntity<>(hemodynamics, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PatientDTO> create(@Valid @RequestBody PatientDTO patientDTO) {
        PatientDTO responseDTO = patientService.create(patientDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PatientDTO> delete(@PathVariable Long id) {
        PatientDTO responseDTO = patientService.delete(id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
