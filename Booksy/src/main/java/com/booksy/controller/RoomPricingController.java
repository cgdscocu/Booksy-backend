package com.booksy.controller;

import com.booksy.entity.RoomPricing;
import com.booksy.entity.RoomPricingId;
import com.booksy.service.RoomPricingService;
import com.booksy.dto.RoomPricingDTO;
import com.booksy.entity.RoomType;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/room-pricings")
public class RoomPricingController {
    private final RoomPricingService roomPricingService;

    
    public RoomPricingController(RoomPricingService roomPricingService) {
        this.roomPricingService = roomPricingService;
    }

    @GetMapping
    public List<RoomPricingDTO> getAllRoomPricings() {
        return roomPricingService.getAllRoomPricings().stream().map(this::toDTO).toList();
    }

    @GetMapping("/{roomTypeId}/{date}")
    public ResponseEntity<RoomPricingDTO> getRoomPricingById(
            @PathVariable UUID roomTypeId,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        RoomPricingId id = new RoomPricingId();
        id.setRoomType(roomTypeId);
        id.setDate(date);
        Optional<RoomPricing> roomPricing = roomPricingService.getRoomPricingById(id);
        return roomPricing.map(value -> ResponseEntity.ok(toDTO(value)))
                          .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public RoomPricingDTO createRoomPricing(@Valid @RequestBody RoomPricingDTO roomPricingDTO) {
        RoomPricing roomPricing = toEntity(roomPricingDTO);
        return toDTO(roomPricingService.saveRoomPricing(roomPricing));
    }

    @PutMapping("/{roomTypeId}/{date}")
    public ResponseEntity<RoomPricingDTO> updateRoomPricing(
            @PathVariable UUID roomTypeId,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @Valid @RequestBody RoomPricingDTO roomPricingDTO) {
        RoomPricingId id = new RoomPricingId();
        id.setRoomType(roomTypeId);
        id.setDate(date);
        RoomPricing updated = roomPricingService.updateRoomPricing(id, toEntity(roomPricingDTO));
        if (updated != null) {
            return ResponseEntity.ok(toDTO(updated));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{roomTypeId}/{date}")
    public ResponseEntity<Void> deleteRoomPricing(
            @PathVariable UUID roomTypeId,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        RoomPricingId id = new RoomPricingId();
        id.setRoomType(roomTypeId);
        id.setDate(date);
        roomPricingService.deleteRoomPricing(id);
        return ResponseEntity.noContent().build();
    }

    // Mapping methods
    private RoomPricingDTO toDTO(RoomPricing roomPricing) {
        RoomPricingDTO dto = new RoomPricingDTO();
        if (roomPricing.getRoomType() != null) {
            dto.setRoomTypeId(roomPricing.getRoomType().getRoomTypeId());
        }
        dto.setDate(roomPricing.getDate());
        dto.setPrice(roomPricing.getPrice());
        return dto;
    }

    private RoomPricing toEntity(RoomPricingDTO dto) {
        RoomPricing roomPricing = new RoomPricing();
        if (dto.getRoomTypeId() != null) {
            RoomType roomType = new RoomType();
            roomType.setRoomTypeId(dto.getRoomTypeId());
            roomPricing.setRoomType(roomType);
        }
        roomPricing.setDate(dto.getDate());
        roomPricing.setPrice(dto.getPrice());
        return roomPricing;
    }
} 