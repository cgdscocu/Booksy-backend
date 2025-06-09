package com.booksy.repository;

import com.booksy.entity.RoomTypeAmenity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface RoomTypeAmenityRepository extends JpaRepository<RoomTypeAmenity, UUID> {
    // Custom queries can be added here
} 