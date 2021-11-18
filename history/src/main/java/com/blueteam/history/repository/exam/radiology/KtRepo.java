package com.blueteam.history.repository.exam.radiology;

import com.blueteam.history.entity.history.exam.radiology.Kt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface KtRepo extends JpaRepository<Kt, Long> {

    @Query("select k from Kt k inner join k.radiology ra join ra.exam ex join ex.history hi join hi.patient pa where pa.id = :patientId ")

    List<Kt> getByUserId(@Param("patientId") long patientId);
}



