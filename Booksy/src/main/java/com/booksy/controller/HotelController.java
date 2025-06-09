package com.booksy.controller;

import com.booksy.entity.Hotel;
import com.booksy.service.HotelService;
import com.booksy.dto.HotelDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {
    private final HotelService hotelService;

    
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping
    public List<HotelDTO> getAllHotels() {
        return hotelService.getAllHotels().stream().map(this::toDTO).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable UUID id) {
        Hotel hotel = hotelService.getHotelById(id);
        return ResponseEntity.ok(hotel);
    }

    @PostMapping
    public HotelDTO createHotel(@Valid @RequestBody HotelDTO hotelDTO) {
        Hotel hotel = toEntity(hotelDTO);
        return toDTO(hotelService.saveHotel(hotel));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HotelDTO> updateHotel(@PathVariable UUID id, @Valid @RequestBody HotelDTO hotelDTO) {
        Hotel updated = hotelService.updateHotel(id, toEntity(hotelDTO));
        if (updated != null) {
            return ResponseEntity.ok(toDTO(updated));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable UUID id) {
        hotelService.deleteHotel(id);
        return ResponseEntity.noContent().build();
    }

    // Mapping methods
    private HotelDTO toDTO(Hotel hotel) {
        HotelDTO dto = new HotelDTO();
        dto.setName(hotel.getName());
        dto.setAddress(hotel.getAddress());
        dto.setDescription(hotel.getDescription());
        dto.setManagerId(hotel.getManagerId());
        return dto;
    }

    private Hotel toEntity(HotelDTO dto) {
        Hotel hotel = new Hotel();
        hotel.setName(dto.getName());
        hotel.setAddress(dto.getAddress());
        hotel.setDescription(dto.getDescription());
        hotel.setManagerId(dto.getManagerId());
        return hotel;
    }
} 