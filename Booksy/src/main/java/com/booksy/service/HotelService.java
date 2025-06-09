package com.booksy.service;

import com.booksy.entity.Hotel;
import com.booksy.repository.HotelRepository;
import com.booksy.exception.ResourceNotFoundException;
import com.booksy.exception.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelService {
    private final HotelRepository hotelRepository;

    
    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public Hotel getHotelById(UUID id) {
        return hotelRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id: " + id));
    }

    public Hotel saveHotel(Hotel hotel) {
        if (hotel.getName() == null || hotel.getName().trim().isEmpty()) {
            throw new BadRequestException("Hotel name cannot be empty");
        }
        return hotelRepository.save(hotel);
    }

    public Hotel updateHotel(UUID id, Hotel hotelDetails) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel", "id", id));

        if (hotelDetails.getName() != null && !hotelDetails.getName().trim().isEmpty()) {
            hotel.setName(hotelDetails.getName());
        }
        if (hotelDetails.getAddress() != null) {
            hotel.setAddress(hotelDetails.getAddress());
        }
        if (hotelDetails.getDescription() != null) {
            hotel.setDescription(hotelDetails.getDescription());
        }
        if (hotelDetails.getManagerId() != null) {
            hotel.setManagerId(hotelDetails.getManagerId());
        }

        return hotelRepository.save(hotel);
    }

    public void deleteHotel(UUID id) {
        if (!hotelRepository.existsById(id)) {
            throw new ResourceNotFoundException("Hotel", "id", id);
        }
        hotelRepository.deleteById(id);
    }
} 