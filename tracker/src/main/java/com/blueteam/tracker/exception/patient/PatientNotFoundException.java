package com.blueteam.tracker.exception.patient;

public class PatientNotFoundException extends RuntimeException {

    private final Object data;
    public PatientNotFoundException(String patientId, Object data) {
        super(String.format("Patient with given id :  {%s}  - Not found", patientId));
        this.data = data;
    }

    public Object getData() {
        return data;
    }
}
