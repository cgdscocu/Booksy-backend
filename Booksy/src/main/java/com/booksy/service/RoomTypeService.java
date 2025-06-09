package com.booksy.service;

import com.booksy.entity.RoomType;
import com.booksy.repository.RoomTypeRepository;
import com.booksy.exception.ResourceNotFoundException;
import com.booksy.exception.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.math.BigDecimal;

@Service
public class RoomTypeService {
    private final RoomTypeRepository roomTypeRepository;

    
    public RoomTypeService(RoomTypeRepository roomTypeRepository) {
        this.roomTypeRepository = roomTypeRepository;
    }

    public List<RoomType> getAllRoomTypes() {
        return roomTypeRepository.findAll();
    }

    public RoomType getRoomTypeById(UUID id) {
        return roomTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("RoomType not found with id: " + id));
    }

    public RoomType saveRoomType(RoomType roomType) {
        validateRoomType(roomType);
        return roomTypeRepository.save(roomType);
    }

    public RoomType updateRoomType(UUID id, RoomType roomTypeDetails) {
        RoomType roomType = roomTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("RoomType", "id", id));

        if (roomTypeDetails.getName() != null && !roomTypeDetails.getName().trim().isEmpty()) {
            roomType.setName(roomTypeDetails.getName());
        }
        if (roomTypeDetails.getDescription() != null) {
            roomType.setDescription(roomTypeDetails.getDescription());
        }
        if (roomTypeDetails.getBasePrice() != null) {
            roomType.setBasePrice(roomTypeDetails.getBasePrice());
        }
        if (roomTypeDetails.getBedType() != null) {
            roomType.setBedType(roomTypeDetails.getBedType());
        }
        if (roomTypeDetails.getMaxOccupancy() != null) {
            roomType.setMaxOccupancy(roomTypeDetails.getMaxOccupancy());
        }
        if (roomTypeDetails.getNumBeds() != null) {
            roomType.setNumBeds(roomTypeDetails.getNumBeds());
        }
        if (roomTypeDetails.getHotel() != null) {
            roomType.setHotel(roomTypeDetails.getHotel());
        }

        return roomTypeRepository.save(roomType);
    }

    public void deleteRoomType(UUID id) {
        if (!roomTypeRepository.existsById(id)) {
            throw new ResourceNotFoundException("RoomType", "id", id);
        }
        roomTypeRepository.deleteById(id);
    }

    private void validateRoomType(RoomType roomType) {
        if (roomType.getName() == null || roomType.getName().trim().isEmpty()) {
            throw new BadRequestException("Room type name cannot be empty");
        }
        if (roomType.getBasePrice() == null || roomType.getBasePrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BadRequestException("Base price must be greater than 0");
        }
        if (roomType.getMaxOccupancy() == null || roomType.getMaxOccupancy() <= 0) {
            throw new BadRequestException("Max occupancy must be greater than 0");
        }
        if (roomType.getNumBeds() == null || roomType.getNumBeds() <= 0) {
            throw new BadRequestException("Number of beds must be greater than 0");
        }
        if (roomType.getHotel() == null) {
            throw new BadRequestException("Hotel cannot be null");
        }
    }
} 