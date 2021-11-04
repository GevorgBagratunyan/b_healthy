package com.blueteam.notifier.repository;

import com.blueteam.notifier.entity.TrackingNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackingNotificationRepository extends JpaRepository<TrackingNotification, Long> {
}
