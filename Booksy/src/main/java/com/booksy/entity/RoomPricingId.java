package com.booksy.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import lombok.Data;

@Data
public class RoomPricingId implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UUID roomType;
    private LocalDate date;
}