package com.booksy.repository;

import com.booksy.entity.RoomInventory;
import com.booksy.entity.RoomInventoryId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomInventoryRepository extends JpaRepository<RoomInventory, RoomInventoryId> {
}