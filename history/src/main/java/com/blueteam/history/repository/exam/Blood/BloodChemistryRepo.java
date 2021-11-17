package com.blueteam.history.repository.exam.Blood;

import com.blueteam.history.entity.history.exam.blood.BloodChemistry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BloodChemistryRepo extends JpaRepository<BloodChemistry, Long> {
}
