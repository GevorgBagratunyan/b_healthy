package com.blueteam.history.repository.treatment;

import com.blueteam.history.entity.history.treatment.ConservativeTreatment;
import com.blueteam.history.entity.history.treatment.SurgicalTreatment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConservativeTreatmentRepo extends JpaRepository<ConservativeTreatment, Long> {
}
