package com.booksy.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "hotel_amenities", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"hotel_id", "amenity_id"})
})
@Data
public class HotelAmenity implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "hotel_id", nullable = false)
    private UUID hotelId;

    @Column(name = "amenity_id", nullable = false)
    private UUID amenityId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "amenity_id", insertable = false, updatable = false)
    private Amenity amenity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id", insertable = false, updatable = false)
    private Hotel hotel;
}

