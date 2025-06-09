package com.booksy.controller;

import com.booksy.dto.RoomDTO;
import com.booksy.entity.Room;
import com.booksy.entity.RoomType;
import com.booksy.service.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    private final RoomService roomService;

    
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public List<RoomDTO> getAllRooms() {
        return roomService.getAllRooms().stream().map(this::toDTO).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable UUID id) {
        Room room = roomService.getRoomById(id);
        return ResponseEntity.ok(room);
    }

    @PostMapping
    public RoomDTO createRoom(@Valid @RequestBody RoomDTO roomDTO) {
        Room room = toEntity(roomDTO);
        return toDTO(roomService.saveRoom(room));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomDTO> updateRoom(@PathVariable UUID id, @Valid @RequestBody RoomDTO roomDTO) {
        Room updated = roomService.updateRoom(id, toEntity(roomDTO));
        if (updated != null) {
            return ResponseEntity.ok(toDTO(updated));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable UUID id) {
        roomService.deleteRoom(id);
        return ResponseEntity.noContent().build();
    }

    // Mapping methods
    private RoomDTO toDTO(Room room) {
        RoomDTO dto = new RoomDTO();
        dto.setRoomNumber(room.getRoomNumber());
        if (room.getRoomType() != null) {
            dto.setRoomTypeId(room.getRoomType().getRoomTypeId());
        }
        return dto;
    }

    private Room toEntity(RoomDTO dto) {
        Room room = new Room();
        room.setRoomNumber(dto.getRoomNumber());
        if (dto.getRoomTypeId() != null) {
            RoomType roomType = new RoomType();
            roomType.setRoomTypeId(dto.getRoomTypeId());
            room.setRoomType(roomType);
        }
        return room;
    }
} 