package com.blueteam.history.dto;

import com.blueteam.history.entity.Doctor;
import com.blueteam.history.entity.Patient;
import lombok.Data;

@Data
public class DoctorDto {
    public DoctorDto(){

    }
    private  Long id;
    private String name;

    public DoctorDto(Doctor doctor) {
        this.id = doctor.getId();
        this.name = doctor.getName();

    }

    public Doctor convertToEntity() {
        Doctor doctor = new Doctor();
        doctor.setName(this.name);

        return doctor;
    }


}
