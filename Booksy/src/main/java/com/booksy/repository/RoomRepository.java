package com.booksy.repository;

import com.booksy.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface RoomRepository extends JpaRepository<Room, UUID> {
    // Custom queries can be added here
} 