package com.blueteam.tracker.exception.doctor;

public class DoctorNotFoundException extends RuntimeException{

    private final Object data;

    public DoctorNotFoundException(String doctorId, Object data) {
        super(String.format("Doctor with given id :  {%s}  - Not found", doctorId));
        this.data = data;
    }

    public Object getData() {
        return data;
    }
}
