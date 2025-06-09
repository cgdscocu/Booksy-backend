package com.booksy.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.UUID;

@Data
public class HotelDTO {
    @NotBlank(message = "Hotel name is required")
    private String name;

    @NotBlank(message = "Address is required")
    private String address;

    private String description;

    @NotNull(message = "Manager ID is required")
    private UUID managerId;
} 