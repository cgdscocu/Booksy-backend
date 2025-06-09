package com.booksy.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.util.UUID;

@Data
public class NotificationDTO {
    @NotNull(message = "User ID is required")
    private UUID userId;

    private UUID reservationId;

    @NotBlank(message = "Message is required")
    private String message;

    @NotNull(message = "isRead is required")
    private Boolean isRead;
} 