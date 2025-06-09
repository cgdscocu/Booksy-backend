package com.booksy.controller;

import com.booksy.entity.Notification;
import com.booksy.service.NotificationService;
import com.booksy.dto.NotificationDTO;
import com.booksy.entity.User;
import com.booksy.entity.Reservation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    private final NotificationService notificationService;

    
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping
    public List<NotificationDTO> getAllNotifications() {
        return notificationService.getAllNotifications().stream().map(this::toDTO).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notification> getNotificationById(@PathVariable UUID id) {
        Notification notification = notificationService.getNotificationById(id);
        return ResponseEntity.ok(notification);
    }

    @PostMapping
    public NotificationDTO createNotification(@Valid @RequestBody NotificationDTO notificationDTO) {
        Notification notification = toEntity(notificationDTO);
        return toDTO(notificationService.saveNotification(notification));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotificationDTO> updateNotification(@PathVariable UUID id, @Valid @RequestBody NotificationDTO notificationDTO) {
        Notification updated = notificationService.updateNotification(id, toEntity(notificationDTO));
        if (updated != null) {
            return ResponseEntity.ok(toDTO(updated));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable UUID id) {
        notificationService.deleteNotification(id);
        return ResponseEntity.noContent().build();
    }

    // Mapping methods
    private NotificationDTO toDTO(Notification notification) {
        NotificationDTO dto = new NotificationDTO();
        if (notification.getUser() != null) dto.setUserId(notification.getUser().getUserId());
        if (notification.getReservation() != null) dto.setReservationId(notification.getReservation().getReservationId());
        dto.setMessage(notification.getMessage());
        dto.setIsRead(notification.getIsRead());
        return dto;
    }

    private Notification toEntity(NotificationDTO dto) {
        Notification notification = new Notification();
        if (dto.getUserId() != null) {
            User user = new User();
            user.setUserId(dto.getUserId());
            notification.setUser(user);
        }
        if (dto.getReservationId() != null) {
            Reservation reservation = new Reservation();
            reservation.setReservationId(dto.getReservationId());
            notification.setReservation(reservation);
        }
        notification.setMessage(dto.getMessage());
        notification.setIsRead(dto.getIsRead());
        return notification;
    }
} 