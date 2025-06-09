package com.booksy.controller;

import com.booksy.entity.Amenity;
import com.booksy.service.AmenityService;
import com.booksy.dto.AmenityDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/amenities")
public class AmenityController {
    private final AmenityService amenityService;

    
    public AmenityController(AmenityService amenityService) {
        this.amenityService = amenityService;
    }

    @GetMapping
    public List<AmenityDTO> getAllAmenities() {
        return amenityService.getAllAmenities().stream().map(this::toDTO).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Amenity> getAmenityById(@PathVariable UUID id) {
        Amenity amenity = amenityService.getAmenityById(id);
        return ResponseEntity.ok(amenity);
    }

    @PostMapping
    public AmenityDTO createAmenity(@Valid @RequestBody AmenityDTO amenityDTO) {
        Amenity amenity = toEntity(amenityDTO);
        return toDTO(amenityService.saveAmenity(amenity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AmenityDTO> updateAmenity(@PathVariable UUID id, @Valid @RequestBody AmenityDTO amenityDTO) {
        Amenity updated = amenityService.updateAmenity(id, toEntity(amenityDTO));
        if (updated != null) {
            return ResponseEntity.ok(toDTO(updated));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAmenity(@PathVariable UUID id) {
        amenityService.deleteAmenity(id);
        return ResponseEntity.noContent().build();
    }

    // Mapping methods
    private AmenityDTO toDTO(Amenity amenity) {
        AmenityDTO dto = new AmenityDTO();
        dto.setName(amenity.getName());
        dto.setDescription(amenity.getDescription());
        return dto;
    }

    private Amenity toEntity(AmenityDTO dto) {
        Amenity amenity = new Amenity();
        amenity.setName(dto.getName());
        amenity.setDescription(dto.getDescription());
        return amenity;
    }
} 