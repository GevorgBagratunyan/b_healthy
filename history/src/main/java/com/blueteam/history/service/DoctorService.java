package com.blueteam.history.service;

import com.blueteam.history.dto.DoctorDto;
import com.blueteam.history.dto.PatientDto;
import com.blueteam.history.entity.Doctor;
import com.blueteam.history.entity.Patient;
import com.blueteam.history.repository.DoctorRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepo doctorRepo;

    public void add(Doctor doctor) {
        doctorRepo.save(doctor);
    }

    public List<Doctor> findAll() {
        return this.doctorRepo.findAll();
    }

    public void update(DoctorDto doctorDto) {

        Doctor existingDoctor = this.doctorRepo.getById(doctorDto.getId());

        existingDoctor.setName(doctorDto.getName());

        this.doctorRepo.save(existingDoctor);

    }
    public void delete(long id) {
        this.doctorRepo.deleteById(id);
    }
}
