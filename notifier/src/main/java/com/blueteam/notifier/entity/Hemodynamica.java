package com.blueteam.notifier.entity;

import javax.persistence.*;
import java.util.Objects;

@Embeddable
public class Hemodynamica {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hemodynamica that = (Hemodynamica) o;
        return Objects.equals(saturation, that.saturation) && Objects.equals(heartRate, that.heartRate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(saturation, heartRate);
    }
}
