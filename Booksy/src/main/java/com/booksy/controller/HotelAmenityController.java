package com.booksy.controller;

import com.booksy.entity.HotelAmenity;
import com.booksy.service.HotelAmenityService;
import com.booksy.dto.HotelAmenityDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/hotel-amenities")
public class HotelAmenityController {
    private final HotelAmenityService hotelAmenityService;


    public HotelAmenityController(HotelAmenityService hotelAmenityService) {
        this.hotelAmenityService = hotelAmenityService;
    }

    @GetMapping
    public List<HotelAmenityDTO> getAllHotelAmenities() {
        return hotelAmenityService.getAllHotelAmenities().stream().map(this::toDTO).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelAmenityDTO> getHotelAmenityById(@PathVariable UUID id) {
        Optional<HotelAmenity> hotelAmenity = hotelAmenityService.getHotelAmenityById(id);
        return hotelAmenity.map(value -> ResponseEntity.ok(toDTO(value)))
                           .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public HotelAmenityDTO createHotelAmenity(@Valid @RequestBody HotelAmenityDTO hotelAmenityDTO) {
        HotelAmenity hotelAmenity = toEntity(hotelAmenityDTO);
        return toDTO(hotelAmenityService.saveHotelAmenity(hotelAmenity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HotelAmenityDTO> updateHotelAmenity(@PathVariable UUID id, @Valid @RequestBody HotelAmenityDTO hotelAmenityDTO) {
        HotelAmenity updated = hotelAmenityService.updateHotelAmenity(id, toEntity(hotelAmenityDTO));
        if (updated != null) {
            return ResponseEntity.ok(toDTO(updated));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotelAmenity(@PathVariable UUID id) {
        hotelAmenityService.deleteHotelAmenity(id);
        return ResponseEntity.noContent().build();
    }

    // Mapping methods
    private HotelAmenityDTO toDTO(HotelAmenity hotelAmenity) {
        HotelAmenityDTO dto = new HotelAmenityDTO();
        dto.setHotelId(hotelAmenity.getHotelId());
        dto.setAmenityId(hotelAmenity.getAmenityId());
        return dto;
    }

    private HotelAmenity toEntity(HotelAmenityDTO dto) {
        HotelAmenity hotelAmenity = new HotelAmenity();
        hotelAmenity.setHotelId(dto.getHotelId());
        hotelAmenity.setAmenityId(dto.getAmenityId());
        return hotelAmenity;
    }
} 