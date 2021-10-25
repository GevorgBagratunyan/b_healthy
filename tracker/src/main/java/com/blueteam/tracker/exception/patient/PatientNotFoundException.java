package com.blueteam.tracker.exception.patient;

public class PatientNotFoundException extends RuntimeException {

    public PatientNotFoundException(String patientId) {
        super(String.format("Patient by id :  {%s}  - Not found", patientId));
    }

}
