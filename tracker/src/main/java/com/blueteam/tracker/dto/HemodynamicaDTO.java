package com.blueteam.tracker.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;

public class HemodynamicaDTO implements DTO{

    @Positive
    @Max(100)
    private Integer saturation;

    @Positive
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
