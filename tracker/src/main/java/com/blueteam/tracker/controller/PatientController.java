package com.blueteam.tracker.controller;

import com.blueteam.tracker.dto.HemodynamicaDTO;
import com.blueteam.tracker.dto.PatientDTO;
import com.blueteam.tracker.service.PatientService;
import com.blueteam.tracker.service.criteria.SearchCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tracker/patient")
public class PatientController {

    private final PatientService patientService;
    private final Logger log;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
        this.log = LoggerFactory.getLogger(PatientController.class);
    }

    @PutMapping("/track/{id}")
    public ResponseEntity<Void> trackHemodynamica(@Valid @RequestBody HemodynamicaDTO hemodynamica,
                                                  @PathVariable Long id) {
        log.info("Tracking parameters accepted: heartRate -> " + hemodynamica.getHeartRate() +
                ", saturation -> " + hemodynamica.getSaturation());
        patientService.trackHemodynamicParams(hemodynamica, id);
        return ResponseEntity.ok().build();
    }

    //If doctor accepts the Alert, then stop tracking and notifying.
    //Or if the patient healed, then switching back to tracking mode.
    @PutMapping("/set-tracking")
    public ResponseEntity<PatientDTO> setTrackingOnOrOff(@RequestParam Long id,
                                                         @RequestParam Boolean isTracking) {
        String set = isTracking ? "ON" : "OFF";
        log.info("Turn tracking -> " + set);
        PatientDTO responseDTO = patientService.setTrackingOnOrOff(id, isTracking);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }


    @PutMapping("/doctors/add")
    public ResponseEntity<PatientDTO> addObservingDoctor(@RequestParam Long doctorId,
                                                         @RequestParam Long patientId) {
        log.info("Add Observing doctor with id: {} to the list of observing doctors " +
                "of patients with id: {}", doctorId, patientId);
        PatientDTO responseDTO = patientService.addDoctor(doctorId, patientId);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PutMapping("/doctors/remove")
    public ResponseEntity<PatientDTO> removeObservingDoctor(@RequestParam Long doctorId,
                                                            @RequestParam Long patientId) {
        log.info("Remove observing doctor from list of patient's observing doctors list");
        PatientDTO responseDTO = patientService.removeDoctor(doctorId, patientId);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientDTO> update(@Valid @RequestBody PatientDTO patientDTO,
                                             @PathVariable Long id) {
        log.info("Update data of Patient with id: " + id);
        PatientDTO responseDTO = patientService.update(patientDTO, id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PatientDTO>> getAll(@RequestBody SearchCriteria criteria) {

        log.info("Get patients with given search criteria parameters => limit: {}, offset: {}", criteria.getLimit(), criteria.getOffset());
        List<PatientDTO> patientDTOs = patientService.getAll(criteria);
        return new ResponseEntity<>(patientDTOs, HttpStatus.OK);
    }

    @GetMapping("/doctors/{id}")
    public ResponseEntity<List<PatientDTO>> getPatientsByDoctorId(@PathVariable Long id) {
        log.info("Get observed patients of observer doctor with id: " + id);
        List<PatientDTO> patientDTOs = patientService.getPatientsByDoctorId(id);
        return new ResponseEntity<>(patientDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> get(@PathVariable Long id) {
        log.info("Get Patient with id: " + id);
        PatientDTO patient = patientService.get(id);
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @GetMapping("/current-hemodynamics/{id}")
    public ResponseEntity<List<HemodynamicaDTO>> getCurrentHemodynamica(@PathVariable Long id) {
        log.info("Get all hemodynamic parameters of patient with id: " + id);
        List<HemodynamicaDTO> hemodynamics = patientService.getCurrentHemodynamics(id);
        return new ResponseEntity<>(hemodynamics, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PatientDTO> create(@Valid @RequestBody PatientDTO patientDTO) {
        log.info("Create patient");
        PatientDTO responseDTO = patientService.create(patientDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PatientDTO> delete(@PathVariable Long id) {
        log.info("Delete patient with id: " + id);
        PatientDTO responseDTO = patientService.delete(id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
