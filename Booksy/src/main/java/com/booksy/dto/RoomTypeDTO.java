package com.booksy.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;
import java.util.UUID;

@Data
public class RoomTypeDTO {
    @NotBlank(message = "Room type name is required")
    private String name;

    private String description;

    @NotNull(message = "Number of beds is required")
    private Integer numBeds;

    @NotBlank(message = "Bed type is required")
    private String bedType;

    @NotNull(message = "Max occupancy is required")
    private Integer maxOccupancy;

    @NotNull(message = "Base price is required")
    private BigDecimal basePrice;

    @NotNull(message = "Hotel ID is required")
    private UUID hotelId;
} 