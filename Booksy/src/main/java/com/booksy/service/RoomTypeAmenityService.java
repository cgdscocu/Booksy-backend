package com.booksy.service;

import com.booksy.entity.RoomTypeAmenity;
import com.booksy.repository.RoomTypeAmenityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoomTypeAmenityService {
    private final RoomTypeAmenityRepository roomTypeAmenityRepository;

    
    public RoomTypeAmenityService(RoomTypeAmenityRepository roomTypeAmenityRepository) {
        this.roomTypeAmenityRepository = roomTypeAmenityRepository;
    }

    public List<RoomTypeAmenity> getAllRoomTypeAmenities() {
        return roomTypeAmenityRepository.findAll();
    }

    public Optional<RoomTypeAmenity> getRoomTypeAmenityById(UUID id) {
        return roomTypeAmenityRepository.findById(id);
    }

    public RoomTypeAmenity saveRoomTypeAmenity(RoomTypeAmenity roomTypeAmenity) {
        return roomTypeAmenityRepository.save(roomTypeAmenity);
    }

    public RoomTypeAmenity updateRoomTypeAmenity(UUID id, RoomTypeAmenity roomTypeAmenityDetails) {
        return roomTypeAmenityRepository.findById(id)
                .map(roomTypeAmenity -> {
                    roomTypeAmenity.setRoomTypeId(roomTypeAmenityDetails.getRoomTypeId());
                    roomTypeAmenity.setAmenityId(roomTypeAmenityDetails.getAmenityId());
                    // Diğer alanlar eklenebilir
                    return roomTypeAmenityRepository.save(roomTypeAmenity);
                })
                .orElse(null);
    }

    public void deleteRoomTypeAmenity(UUID id) {
        roomTypeAmenityRepository.deleteById(id);
    }
} 