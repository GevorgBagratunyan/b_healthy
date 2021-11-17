package com.blueteam.history.repository.exam.Blood;

import com.blueteam.history.entity.history.exam.blood.BloodGeneral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloodGeneralRepo extends JpaRepository<BloodGeneral, Long> {
}
