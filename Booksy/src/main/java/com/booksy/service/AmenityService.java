package com.booksy.service;

import com.booksy.entity.Amenity;
import com.booksy.repository.AmenityRepository;
import com.booksy.exception.ResourceNotFoundException;
import com.booksy.exception.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AmenityService {
    private final AmenityRepository amenityRepository;

    
    public AmenityService(AmenityRepository amenityRepository) {
        this.amenityRepository = amenityRepository;
    }

    public List<Amenity> getAllAmenities() {
        return amenityRepository.findAll();
    }

    public Amenity getAmenityById(UUID id) {
        return amenityRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Amenity not found with id: " + id));
    }

    public Amenity saveAmenity(Amenity amenity) {
        validateAmenity(amenity);
        return amenityRepository.save(amenity);
    }

    public Amenity updateAmenity(UUID id, Amenity amenityDetails) {
        Amenity amenity = amenityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Amenity", "id", id));

        if (amenityDetails.getName() != null && !amenityDetails.getName().trim().isEmpty()) {
            amenity.setName(amenityDetails.getName());
        }
        if (amenityDetails.getDescription() != null) {
            amenity.setDescription(amenityDetails.getDescription());
        }

        return amenityRepository.save(amenity);
    }

    public void deleteAmenity(UUID id) {
        if (!amenityRepository.existsById(id)) {
            throw new ResourceNotFoundException("Amenity", "id", id);
        }
        amenityRepository.deleteById(id);
    }

    private void validateAmenity(Amenity amenity) {
        if (amenity.getName() == null || amenity.getName().trim().isEmpty()) {
            throw new BadRequestException("Amenity name cannot be empty");
        }
    }
} 