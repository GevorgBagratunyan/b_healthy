package com.blueteam.tracker.entity.observer;

public interface Observed {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(String msg);
}
