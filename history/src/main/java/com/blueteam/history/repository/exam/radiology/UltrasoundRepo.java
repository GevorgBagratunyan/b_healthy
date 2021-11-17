package com.blueteam.history.repository.exam.radiology;


import com.blueteam.history.entity.history.exam.radiology.Ultrasound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UltrasoundRepo extends JpaRepository<Ultrasound, Long> {
}
