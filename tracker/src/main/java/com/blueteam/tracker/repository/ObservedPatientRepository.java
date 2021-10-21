package com.blueteam.tracker.repository;

import com.blueteam.tracker.entity.observer.ObservedPatient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObservedPatientRepository extends JpaRepository<ObservedPatient, Long> {
}
