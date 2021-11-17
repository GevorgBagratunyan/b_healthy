package com.blueteam.history.repository;

import com.blueteam.history.entity.history.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepo extends JpaRepository<History, Long> {
}
