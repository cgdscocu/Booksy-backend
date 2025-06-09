package com.booksy.service;

import com.booksy.entity.HotelAmenity;
import com.booksy.repository.HotelAmenityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HotelAmenityService {
    private final HotelAmenityRepository hotelAmenityRepository;

    
    public HotelAmenityService(HotelAmenityRepository hotelAmenityRepository) {
        this.hotelAmenityRepository = hotelAmenityRepository;
    }

    public List<HotelAmenity> getAllHotelAmenities() {
        return hotelAmenityRepository.findAll();
    }

    public Optional<HotelAmenity> getHotelAmenityById(UUID id) {
        return hotelAmenityRepository.findById(id);
    }

    public HotelAmenity saveHotelAmenity(HotelAmenity hotelAmenity) {
        return hotelAmenityRepository.save(hotelAmenity);
    }

    public HotelAmenity updateHotelAmenity(UUID id, HotelAmenity hotelAmenityDetails) {
        return hotelAmenityRepository.findById(id)
                .map(hotelAmenity -> {
                    hotelAmenity.setHotelId(hotelAmenityDetails.getHotelId());
                    hotelAmenity.setAmenityId(hotelAmenityDetails.getAmenityId());
                    // Diğer alanlar eklenebilir
                    return hotelAmenityRepository.save(hotelAmenity);
                })
                .orElse(null);
    }

    public void deleteHotelAmenity(UUID id) {
        hotelAmenityRepository.deleteById(id);
    }
} 