package com.booksy.service;

import com.booksy.entity.RoomPricing;
import com.booksy.entity.RoomPricingId;
import com.booksy.repository.RoomPricingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class RoomPricingService {
    private final RoomPricingRepository roomPricingRepository;

    
    public RoomPricingService(RoomPricingRepository roomPricingRepository) {
        this.roomPricingRepository = roomPricingRepository;
    }

    public List<RoomPricing> getAllRoomPricings() {
        return roomPricingRepository.findAll();
    }

    public Optional<RoomPricing> getRoomPricingById(RoomPricingId id) {
        return roomPricingRepository.findById(id);
    }

    public RoomPricing saveRoomPricing(RoomPricing roomPricing) {
        return roomPricingRepository.save(roomPricing);
    }

    public RoomPricing updateRoomPricing(RoomPricingId id, RoomPricing roomPricingDetails) {
        return roomPricingRepository.findById(id)
                .map(roomPricing -> {
                    roomPricing.setDate(roomPricingDetails.getDate());
                    roomPricing.setPrice(roomPricingDetails.getPrice());
                    roomPricing.setRoomType(roomPricingDetails.getRoomType());
                    // Diğer alanlar eklenebilir
                    return roomPricingRepository.save(roomPricing);
                })
                .orElse(null);
    }

    public void deleteRoomPricing(RoomPricingId id) {
        roomPricingRepository.deleteById(id);
    }
}