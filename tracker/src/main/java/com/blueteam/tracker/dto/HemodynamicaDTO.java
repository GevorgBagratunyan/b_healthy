package com.blueteam.tracker.dto;

public class HemodynamicaDTO implements DTO{

    private Integer saturation;
    private Integer heartRate;

    public Integer getSaturation() {
        return saturation;
    }

    public void setSaturation(Integer saturation) {
        this.saturation = saturation;
    }

    public Integer getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(Integer heartRate) {
        this.heartRate = heartRate;
    }
}
