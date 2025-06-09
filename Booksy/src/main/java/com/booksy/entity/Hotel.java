package com.booksy.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "hotels")
@Data
public class Hotel {
    @Id
    @Column(name = "hotel_id")
    private UUID hotelId;

    @Column(name = "manager_id")
    private UUID managerId;

    private String name;
    private String description;
    private String address;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToMany
    @JoinTable(
        name = "hotel_amenities",
        joinColumns = @JoinColumn(name = "hotel_id"),
        inverseJoinColumns = @JoinColumn(name = "amenity_id")
    )
    private java.util.Set<Amenity> amenities = new java.util.HashSet<>();
}
