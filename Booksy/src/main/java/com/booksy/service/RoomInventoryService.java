package com.booksy.service;

import com.booksy.entity.RoomInventory;
import com.booksy.entity.RoomInventoryId;
import com.booksy.repository.RoomInventoryRepository;
import com.booksy.exception.ResourceNotFoundException;
import com.booksy.exception.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomInventoryService {
    private final RoomInventoryRepository roomInventoryRepository;

    
    public RoomInventoryService(RoomInventoryRepository roomInventoryRepository) {
        this.roomInventoryRepository = roomInventoryRepository;
    }

    public List<RoomInventory> getAllRoomInventories() {
        return roomInventoryRepository.findAll();
    }

    public RoomInventory getRoomInventoryById(RoomInventoryId id) {
        return roomInventoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("RoomInventory not found with id: " + id));
    }

    public RoomInventory saveRoomInventory(RoomInventory roomInventory) {
        validateRoomInventory(roomInventory);
        return roomInventoryRepository.save(roomInventory);
    }

    public RoomInventory updateRoomInventory(RoomInventoryId id, RoomInventory roomInventoryDetails) {
        RoomInventory roomInventory = roomInventoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("RoomInventory", "id", id));

        if (roomInventoryDetails.getDate() != null) {
            roomInventory.setDate(roomInventoryDetails.getDate());
        }
        if (roomInventoryDetails.getAvailableRooms() != null) {
            roomInventory.setAvailableRooms(roomInventoryDetails.getAvailableRooms());
        }
        if (roomInventoryDetails.getRoomType() != null) {
            roomInventory.setRoomType(roomInventoryDetails.getRoomType());
        }

        return roomInventoryRepository.save(roomInventory);
    }

    public void deleteRoomInventory(RoomInventoryId id) {
        if (!roomInventoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("RoomInventory", "id", id);
        }
        roomInventoryRepository.deleteById(id);
    }

    private void validateRoomInventory(RoomInventory roomInventory) {
        if (roomInventory.getDate() == null) {
            throw new BadRequestException("Date cannot be null");
        }
        if (roomInventory.getAvailableRooms() == null || roomInventory.getAvailableRooms() < 0) {
            throw new BadRequestException("Available rooms cannot be negative");
        }
        if (roomInventory.getRoomType() == null) {
            throw new BadRequestException("Room type cannot be null");
        }
    }
}