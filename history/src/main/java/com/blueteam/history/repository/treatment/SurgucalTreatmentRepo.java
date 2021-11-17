package com.blueteam.history.repository.treatment;

import com.blueteam.history.entity.history.treatment.SurgicalTreatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurgucalTreatmentRepo extends JpaRepository<SurgicalTreatment, Long> {
}
