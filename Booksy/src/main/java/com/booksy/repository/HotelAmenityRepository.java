package com.booksy.repository;

import com.booksy.entity.HotelAmenity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface HotelAmenityRepository extends JpaRepository<HotelAmenity, UUID> {
    // Custom queries can be added here
} 