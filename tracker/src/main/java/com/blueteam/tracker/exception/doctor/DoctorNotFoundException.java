package com.blueteam.tracker.exception.doctor;

public class DoctorNotFoundException extends RuntimeException{

    public DoctorNotFoundException(String doctorId) {
        super(String.format("Doctor by id :  {%s}  - Not found", doctorId));
    }
}
