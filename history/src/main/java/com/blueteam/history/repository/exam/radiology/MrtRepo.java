package com.blueteam.history.repository.exam.radiology;
import com.blueteam.history.entity.history.exam.radiology.Mrt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface MrtRepo extends JpaRepository<Mrt, Long>  {
}
