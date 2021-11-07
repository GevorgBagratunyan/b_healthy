package com.blueteam.tracker.exception.patient;

public class PatientNotFoundException extends RuntimeException {

    private final Object data;
    public PatientNotFoundException(String patientId, Object data) {
        super(String.format("Patient by id :  {%s}  - Not found", patientId));
        this.data = data;
    }

    public Object getData() {
        return data;
    }
}
