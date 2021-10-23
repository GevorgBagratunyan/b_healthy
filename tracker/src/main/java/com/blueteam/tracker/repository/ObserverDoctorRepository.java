package com.blueteam.tracker.repository;

import com.blueteam.tracker.entity.ObserverDoctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObserverDoctorRepository extends JpaRepository<ObserverDoctor, Long> {
}
