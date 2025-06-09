package com.booksy.repository;

import com.booksy.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface HotelRepository extends JpaRepository<Hotel, UUID> {
    // Custom queries can be added here
} 