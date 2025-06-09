package com.booksy.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.UUID;

@Entity
@Table(name = "amenities")
@Data
public class Amenity {
    @Id
    @Column(name = "amenity_id")
    private UUID amenityId;

    private String name;
    private String description;

    @ManyToMany(mappedBy = "amenities")
    private java.util.Set<RoomType> roomTypes = new java.util.HashSet<>();

    @ManyToMany(mappedBy = "amenities")
    private java.util.Set<Hotel> hotels = new java.util.HashSet<>();
}
