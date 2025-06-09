package com.booksy.repository;

import com.booksy.entity.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface RoomTypeRepository extends JpaRepository<RoomType, UUID> {
    // Custom queries can be added here
} 