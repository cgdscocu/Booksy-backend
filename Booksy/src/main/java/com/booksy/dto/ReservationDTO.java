package com.booksy.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class ReservationDTO {
    @NotNull(message = "User ID is required")
    private UUID userId;

    @NotNull(message = "Hotel ID is required")
    private UUID hotelId;

    @NotNull(message = "Room type ID is required")
    private UUID roomTypeId;

    @NotNull(message = "Check-in date is required")
    private LocalDate checkInDate;

    @NotNull(message = "Check-out date is required")
    private LocalDate checkOutDate;

    @NotNull(message = "Total price is required")
    private BigDecimal totalPrice;

    @NotBlank(message = "Status is required")
    private String status;
} 