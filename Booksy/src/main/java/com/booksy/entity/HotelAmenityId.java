package com.booksy.entity;

import java.io.Serializable;
import java.util.UUID;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class HotelAmenityId implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private UUID amenityId;
    private UUID hotelId;
} 