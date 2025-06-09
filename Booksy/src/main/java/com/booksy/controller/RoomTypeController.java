package com.booksy.controller;

import com.booksy.dto.RoomTypeDTO;
import com.booksy.entity.Hotel;
import com.booksy.entity.RoomType;
import com.booksy.service.RoomTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/room-types")
public class RoomTypeController {
    private final RoomTypeService roomTypeService;

    
    public RoomTypeController(RoomTypeService roomTypeService) {
        this.roomTypeService = roomTypeService;
    }

    @GetMapping
    public List<RoomTypeDTO> getAllRoomTypes() {
        return roomTypeService.getAllRoomTypes().stream().map(this::toDTO).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomType> getRoomTypeById(@PathVariable UUID id) {
        RoomType roomType = roomTypeService.getRoomTypeById(id);
        return ResponseEntity.ok(roomType);
    }

    @PostMapping
    public RoomTypeDTO createRoomType(@Valid @RequestBody RoomTypeDTO roomTypeDTO) {
        RoomType roomType = toEntity(roomTypeDTO);
        return toDTO(roomTypeService.saveRoomType(roomType));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomTypeDTO> updateRoomType(@PathVariable UUID id, @Valid @RequestBody RoomTypeDTO roomTypeDTO) {
        RoomType updated = roomTypeService.updateRoomType(id, toEntity(roomTypeDTO));
        if (updated != null) {
            return ResponseEntity.ok(toDTO(updated));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoomType(@PathVariable UUID id) {
        roomTypeService.deleteRoomType(id);
        return ResponseEntity.noContent().build();
    }

    // Mapping methods
    private RoomTypeDTO toDTO(RoomType roomType) {
        RoomTypeDTO dto = new RoomTypeDTO();
        dto.setName(roomType.getName());
        dto.setDescription(roomType.getDescription());
        dto.setNumBeds(roomType.getNumBeds());
        dto.setBedType(roomType.getBedType());
        dto.setMaxOccupancy(roomType.getMaxOccupancy());
        dto.setBasePrice(roomType.getBasePrice());
        if (roomType.getHotel() != null) {
            dto.setHotelId(roomType.getHotel().getHotelId());
        }
        return dto;
    }

    private RoomType toEntity(RoomTypeDTO dto) {
        RoomType roomType = new RoomType();
        roomType.setName(dto.getName());
        roomType.setDescription(dto.getDescription());
        roomType.setNumBeds(dto.getNumBeds());
        roomType.setBedType(dto.getBedType());
        roomType.setMaxOccupancy(dto.getMaxOccupancy());
        roomType.setBasePrice(dto.getBasePrice());
        if (dto.getHotelId() != null) {
            Hotel hotel = new Hotel();
            hotel.setHotelId(dto.getHotelId());
            roomType.setHotel(hotel);
        }
        return roomType;
    }
} 