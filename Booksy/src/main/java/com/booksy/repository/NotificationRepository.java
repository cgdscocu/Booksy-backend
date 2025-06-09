package com.booksy.repository;

import com.booksy.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface NotificationRepository extends JpaRepository<Notification, UUID> {
    // Custom queries can be added here
} 