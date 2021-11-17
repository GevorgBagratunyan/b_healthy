package com.blueteam.appointment.dto;

import javax.validation.constraints.NotNull;

public class AppointmentDTO {

    @NotNull
    private Long patientId;
    @NotNull
    private Long doctorId;
    private String date;  // "dd-MM-yyyy HH:mm"
    private Boolean isAccepted;
    private Boolean isCanceled;

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getAccepted() {
        return isAccepted;
    }

    public void setAccepted(Boolean accepted) {
        isAccepted = accepted;
    }

    public Boolean getCanceled() {
        return isCanceled;
    }

    public void setCanceled(Boolean canceled) {
        isCanceled = canceled;
    }
}
