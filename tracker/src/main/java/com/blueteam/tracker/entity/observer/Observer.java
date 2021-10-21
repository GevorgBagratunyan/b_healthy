package com.blueteam.tracker.entity.observer;


import com.blueteam.tracker.entity.Hemodynamica;

public interface Observer {

    void handleEvent(Hemodynamica hemodynamica, Long patientId);
}
