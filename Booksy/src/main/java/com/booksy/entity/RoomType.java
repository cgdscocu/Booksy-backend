package com.booksy.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "room_types")
@Data
public class RoomType {
	@Id
	@Column(name = "room_type_id")
	private UUID roomTypeId;

	@ManyToOne
	@JoinColumn(name = "hotel_id")
	private Hotel hotel;

	private String name;
	private String description;

	@Column(name = "num_beds")
	private Integer numBeds;

	@Column(name = "bed_type")
	private String bedType;

	@Column(name = "max_occupancy")
	private Integer maxOccupancy;

	@Column(name = "base_price")
	private BigDecimal basePrice;

	@ManyToMany
	@JoinTable(
		name = "room_type_amenities",
		joinColumns = @JoinColumn(name = "room_type_id"),
		inverseJoinColumns = @JoinColumn(name = "amenity_id")
	)
	private java.util.Set<Amenity> amenities = new java.util.HashSet<>();
}
