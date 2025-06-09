package com.booksy.repository;

import com.booksy.entity.Amenity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface AmenityRepository extends JpaRepository<Amenity, UUID> {
    // Custom queries can be added here
} 