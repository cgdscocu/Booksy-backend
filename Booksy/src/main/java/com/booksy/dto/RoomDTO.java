package com.booksy.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.UUID;

@Data
public class RoomDTO {
    @NotBlank(message = "Room number is required")
    private String roomNumber;

    @NotNull(message = "Room type ID is required")
    private UUID roomTypeId;
}
