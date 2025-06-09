package com.booksy.entity;


	import jakarta.persistence.*;
	import lombok.Data;
	import java.util.UUID;

	@Entity
	@Table(name = "rooms")
	@Data
	public class Room {
	    @Id
	    @Column(name = "room_id")
	    private UUID roomId;

	    @ManyToOne
	    @JoinColumn(name = "room_type_id")
	    private RoomType roomType;

	    @Column(name = "room_number")
	    private String roomNumber;
	}

