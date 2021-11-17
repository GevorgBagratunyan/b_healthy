package com.blueteam.history.repository.exam.radiology;

import com.blueteam.history.entity.history.exam.radiology.Radiology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RadiologyRepo extends JpaRepository<Radiology, Long> {
}
