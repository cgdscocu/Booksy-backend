package com.booksy.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AmenityDTO {
    @NotBlank(message = "Amenity name is required")
    private String name;

    private String description;
} 