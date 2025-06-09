package com.booksy.service;

import com.booksy.entity.Room;
import com.booksy.repository.RoomRepository;
import com.booksy.exception.ResourceNotFoundException;
import com.booksy.exception.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RoomService {
    private final RoomRepository roomRepository;

    
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room getRoomById(UUID id) {
        return roomRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Room not found with id: " + id));
    }

    public Room saveRoom(Room room) {
        validateRoom(room);
        return roomRepository.save(room);
    }

    public Room updateRoom(UUID id, Room roomDetails) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room", "id", id));

        if (roomDetails.getRoomNumber() != null && !roomDetails.getRoomNumber().trim().isEmpty()) {
            room.setRoomNumber(roomDetails.getRoomNumber());
        }
        if (roomDetails.getRoomType() != null) {
            room.setRoomType(roomDetails.getRoomType());
        }

        return roomRepository.save(room);
    }

    public void deleteRoom(UUID id) {
        if (!roomRepository.existsById(id)) {
            throw new ResourceNotFoundException("Room", "id", id);
        }
        roomRepository.deleteById(id);
    }

    private void validateRoom(Room room) {
        if (room.getRoomNumber() == null || room.getRoomNumber().trim().isEmpty()) {
            throw new BadRequestException("Room number cannot be empty");
        }
        if (room.getRoomType() == null) {
            throw new BadRequestException("Room type cannot be null");
        }
    }
} 