package com.booksy.service;

import com.booksy.entity.Notification;
import com.booksy.repository.NotificationRepository;
import com.booksy.exception.ResourceNotFoundException;
import com.booksy.exception.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;

    
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    public Notification getNotificationById(UUID id) {
        return notificationRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Notification not found with id: " + id));
    }

    public Notification saveNotification(Notification notification) {
        validateNotification(notification);
        return notificationRepository.save(notification);
    }

    public Notification updateNotification(UUID id, Notification notificationDetails) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notification", "id", id));

        if (notificationDetails.getMessage() != null && !notificationDetails.getMessage().trim().isEmpty()) {
            notification.setMessage(notificationDetails.getMessage());
        }
        if (notificationDetails.getIsRead() != null) {
            notification.setIsRead(notificationDetails.getIsRead());
        }
        if (notificationDetails.getReservation() != null) {
            notification.setReservation(notificationDetails.getReservation());
        }
        if (notificationDetails.getUser() != null) {
            notification.setUser(notificationDetails.getUser());
        }

        return notificationRepository.save(notification);
    }

    public void deleteNotification(UUID id) {
        if (!notificationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Notification", "id", id);
        }
        notificationRepository.deleteById(id);
    }

    private void validateNotification(Notification notification) {
        if (notification.getMessage() == null || notification.getMessage().trim().isEmpty()) {
            throw new BadRequestException("Message cannot be empty");
        }
        if (notification.getIsRead() == null) {
            throw new BadRequestException("IsRead status cannot be null");
        }
        if (notification.getUser() == null) {
            throw new BadRequestException("User cannot be null");
        }
    }
} 