package com.booksy.repository;

import com.booksy.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ReservationRepository extends JpaRepository<Reservation, UUID> {
    // Custom queries can be added here
} 