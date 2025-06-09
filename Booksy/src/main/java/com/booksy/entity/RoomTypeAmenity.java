package com.booksy.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "room_type_amenities", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"room_type_id", "amenity_id"})
})
@Data
public class RoomTypeAmenity implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "room_type_id", nullable = false)
    private UUID roomTypeId;

    @Column(name = "amenity_id", nullable = false)
    private UUID amenityId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "amenity_id", insertable = false, updatable = false)
    private Amenity amenity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_type_id", insertable = false, updatable = false)
    private RoomType roomType;
}
