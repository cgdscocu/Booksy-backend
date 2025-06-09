package com.booksy.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class RoomPricingDTO {
    @NotNull(message = "Room type ID is required")
    private UUID roomTypeId;

    @NotNull(message = "Date is required")
    private LocalDate date;

    @NotNull(message = "Price is required")
    private BigDecimal price;
} 