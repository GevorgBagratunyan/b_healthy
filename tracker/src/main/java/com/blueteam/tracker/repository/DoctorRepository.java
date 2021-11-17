package com.blueteam.tracker.repository;

import com.blueteam.tracker.entity.Doctor;
import com.blueteam.tracker.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    @Query("SELECT d FROM Doctor d LEFT JOIN FETCH d.patients p WHERE d.id = ?1")
    Optional<Doctor> findById(Long id);
}
