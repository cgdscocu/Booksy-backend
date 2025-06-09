package com.booksy.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.UUID;

@Data
public class RoomTypeAmenityDTO {
    @NotNull(message = "Room type ID is required")
    private UUID roomTypeId;

    @NotNull(message = "Amenity ID is required")
    private UUID amenityId;
} 