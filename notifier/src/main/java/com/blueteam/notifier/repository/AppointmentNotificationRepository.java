package com.blueteam.notifier.repository;

import com.blueteam.notifier.entity.AppointmentNotification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentNotificationRepository extends JpaRepository<AppointmentNotification, Long> {
}
