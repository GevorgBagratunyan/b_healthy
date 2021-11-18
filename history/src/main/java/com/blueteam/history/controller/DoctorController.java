package com.blueteam.history.controller;

import com.blueteam.history.dto.DoctorDto;
import com.blueteam.history.dto.PatientDto;
import com.blueteam.history.entity.Doctor;
import com.blueteam.history.entity.Patient;
import com.blueteam.history.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private  final DoctorService doctorService;

    @GetMapping
    public List<DoctorDto> doctorDtoList() {

        return doctorService.findAll()
                .stream()
                .map(DoctorDto::new)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void addNewDoctor(@RequestBody DoctorDto doctorDto) {
        Doctor doctor = doctorDto.convertToEntity();
        doctorService.add(doctor);

    }

    @PutMapping("/{id}")
    public void update(@RequestBody DoctorDto doctorDto) {
        doctorService.update(doctorDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id) {
        doctorService.delete(id);
    }
}
