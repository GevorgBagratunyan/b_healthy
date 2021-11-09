package com.blueteam.tracker.repository;

import com.blueteam.tracker.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    @Query("SELECT p FROM Patient p JOIN FETCH p.doctors d WHERE p.id = ?1")
    Optional<Patient> findById(Long id);
}
