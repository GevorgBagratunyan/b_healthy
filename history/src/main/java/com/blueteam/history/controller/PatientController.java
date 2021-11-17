package com.blueteam.history.controller;

import com.blueteam.history.dto.PatientDto;
import com.blueteam.history.entity.Patient;
import com.blueteam.history.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping
    public List<PatientDto> listPatiants() {

        return patientService.findAll()
                .stream()
                .map(PatientDto::new)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void addNewPatient(@RequestBody PatientDto patientDto) {

        Patient patient = patientDto.convertToEntity();
        patientService.add(patient);

    }


    @PutMapping("/{id}")
    public void update(@RequestBody PatientDto patientDto) {
//        Patient patient = patientDto.convertToEntity();
        patientService.update(patientDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id) {
        patientService.delete(id);
    }

}
