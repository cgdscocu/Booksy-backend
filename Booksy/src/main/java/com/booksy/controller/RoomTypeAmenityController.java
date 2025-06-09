package com.booksy.controller;

import com.booksy.entity.RoomTypeAmenity;
import com.booksy.service.RoomTypeAmenityService;
import com.booksy.dto.RoomTypeAmenityDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/room-type-amenities")
public class RoomTypeAmenityController {
    private final RoomTypeAmenityService roomTypeAmenityService;

    
    public RoomTypeAmenityController(RoomTypeAmenityService roomTypeAmenityService) {
        this.roomTypeAmenityService = roomTypeAmenityService;
    }

    @GetMapping
    public List<RoomTypeAmenityDTO> getAllRoomTypeAmenities() {
        return roomTypeAmenityService.getAllRoomTypeAmenities().stream()
                .map(this::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomTypeAmenityDTO> getRoomTypeAmenityById(@PathVariable UUID id) {
        Optional<RoomTypeAmenity> roomTypeAmenity = roomTypeAmenityService.getRoomTypeAmenityById(id);
        return roomTypeAmenity.map(value -> ResponseEntity.ok(toDTO(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public RoomTypeAmenityDTO createRoomTypeAmenity(@Valid @RequestBody RoomTypeAmenityDTO roomTypeAmenityDTO) {
        RoomTypeAmenity roomTypeAmenity = toEntity(roomTypeAmenityDTO);
        return toDTO(roomTypeAmenityService.saveRoomTypeAmenity(roomTypeAmenity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomTypeAmenityDTO> updateRoomTypeAmenity(
            @PathVariable UUID id,
            @Valid @RequestBody RoomTypeAmenityDTO roomTypeAmenityDTO) {
        RoomTypeAmenity updated = roomTypeAmenityService.updateRoomTypeAmenity(id, toEntity(roomTypeAmenityDTO));
        if (updated != null) {
            return ResponseEntity.ok(toDTO(updated));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoomTypeAmenity(@PathVariable UUID id) {
        roomTypeAmenityService.deleteRoomTypeAmenity(id);
        return ResponseEntity.noContent().build();
    }

    private RoomTypeAmenityDTO toDTO(RoomTypeAmenity roomTypeAmenity) {
        RoomTypeAmenityDTO dto = new RoomTypeAmenityDTO();
        dto.setRoomTypeId(roomTypeAmenity.getRoomTypeId());
        dto.setAmenityId(roomTypeAmenity.getAmenityId());
        return dto;
    }

    private RoomTypeAmenity toEntity(RoomTypeAmenityDTO dto) {
        RoomTypeAmenity roomTypeAmenity = new RoomTypeAmenity();
        roomTypeAmenity.setRoomTypeId(dto.getRoomTypeId());
        roomTypeAmenity.setAmenityId(dto.getAmenityId());
        return roomTypeAmenity;
    }
} 