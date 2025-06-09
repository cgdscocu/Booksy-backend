package com.booksy.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.UUID;

@Data
public class HotelAmenityDTO {
    @NotNull(message = "Hotel ID is required")
    private UUID hotelId;

    @NotNull(message = "Amenity ID is required")
    private UUID amenityId;
} 