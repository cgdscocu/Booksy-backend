package com.booksy.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import lombok.Data;

@Data
public class RoomInventoryId implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UUID roomType;
    private LocalDate date;

    public RoomInventoryId(UUID roomType, LocalDate date) {
        this.roomType = roomType;
        this.date = date;
    }

    public RoomInventoryId() {}
}