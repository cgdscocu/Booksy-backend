package com.booksy.controller;

import com.booksy.entity.RoomInventory;
import com.booksy.entity.RoomInventoryId;
import com.booksy.service.RoomInventoryService;
import com.booksy.dto.RoomInventoryDTO;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/room-inventories")
public class RoomInventoryController {
    private final RoomInventoryService roomInventoryService;

    public RoomInventoryController(RoomInventoryService roomInventoryService) {
        this.roomInventoryService = roomInventoryService;
    }

    @GetMapping
    public List<RoomInventoryDTO> getAllRoomInventories() {
        return roomInventoryService.getAllRoomInventories().stream().map(this::toDTO).toList();
    }

    @GetMapping("/{roomTypeId}/{date}")
    public ResponseEntity<RoomInventory> getRoomInventoryById(@PathVariable UUID roomTypeId, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        RoomInventoryId id = new RoomInventoryId(roomTypeId, date);
        RoomInventory roomInventory = roomInventoryService.getRoomInventoryById(id);
        return ResponseEntity.ok(roomInventory);
    }

    @PostMapping
    public RoomInventoryDTO createRoomInventory(@Valid @RequestBody RoomInventoryDTO roomInventoryDTO) {
        RoomInventory roomInventory = toEntity(roomInventoryDTO);
        return toDTO(roomInventoryService.saveRoomInventory(roomInventory));
    }

    @PutMapping("/{roomTypeId}/{date}")
    public ResponseEntity<RoomInventoryDTO> updateRoomInventory(
            @PathVariable UUID roomTypeId,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @Valid @RequestBody RoomInventoryDTO roomInventoryDTO) {
        RoomInventoryId id = new RoomInventoryId();
        id.setRoomType(roomTypeId);
        id.setDate(date);
        RoomInventory updated = roomInventoryService.updateRoomInventory(id, toEntity(roomInventoryDTO));
        if (updated != null) {
            return ResponseEntity.ok(toDTO(updated));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{roomTypeId}/{date}")
    public ResponseEntity<Void> deleteRoomInventory(
            @PathVariable UUID roomTypeId,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        RoomInventoryId id = new RoomInventoryId();
        id.setRoomType(roomTypeId);
        id.setDate(date);
        roomInventoryService.deleteRoomInventory(id);
        return ResponseEntity.noContent().build();
    }

    // Mapping methods
    private RoomInventoryDTO toDTO(RoomInventory roomInventory) {
        RoomInventoryDTO dto = new RoomInventoryDTO();
        dto.setRoomTypeId(roomInventory.getRoomType());
        dto.setDate(roomInventory.getDate());
        dto.setAvailableRooms(roomInventory.getAvailableRooms());
        return dto;
    }

    private RoomInventory toEntity(RoomInventoryDTO dto) {
        RoomInventory roomInventory = new RoomInventory();
        roomInventory.setRoomType(dto.getRoomTypeId());
        roomInventory.setDate(dto.getDate());
        roomInventory.setAvailableRooms(dto.getAvailableRooms());
        return roomInventory;
    }
} 