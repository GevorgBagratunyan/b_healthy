package com.blueteam.history.repository.exam.Blood;

import com.blueteam.history.entity.history.exam.blood.BloodAnalize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloodRepository extends JpaRepository<BloodAnalize, Long> {
}
