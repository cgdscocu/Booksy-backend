package com.booksy.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "notifications")
@Data
public class Notification {
	@Id
	@Column(name = "notification_id")
	private UUID notificationId;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "reservation_id")
	private Reservation reservation;

	private String message;

	@Column(name = "is_read")
	private Boolean isRead;

	@Column(name = "created_at")
	private LocalDateTime createdAt;
}
