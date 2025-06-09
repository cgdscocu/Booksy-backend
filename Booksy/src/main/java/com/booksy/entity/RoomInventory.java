package com.booksy.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "room_inventory")
@Data
@IdClass(RoomInventoryId.class)
public class RoomInventory implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "room_type_id")
    private UUID roomType;

    @Id
    private LocalDate date;

    @Column(name = "available_rooms")
    private Integer availableRooms;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}