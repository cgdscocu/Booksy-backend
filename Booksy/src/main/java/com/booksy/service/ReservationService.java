package com.booksy.service;

import com.booksy.entity.Reservation;
import com.booksy.repository.ReservationRepository;
import com.booksy.exception.ResourceNotFoundException;
import com.booksy.exception.BadRequestException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.math.BigDecimal;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation getReservationById(UUID id) {
        return reservationRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Reservation not found with id: " + id));
    }

    public Reservation saveReservation(Reservation reservation) {
        validateReservation(reservation);
        return reservationRepository.save(reservation);
    }

    public Reservation updateReservation(UUID id, Reservation reservationDetails) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation", "id", id));

        if (reservationDetails.getCheckInDate() != null) {
            reservation.setCheckInDate(reservationDetails.getCheckInDate());
        }
        if (reservationDetails.getCheckOutDate() != null) {
            reservation.setCheckOutDate(reservationDetails.getCheckOutDate());
        }
        if (reservationDetails.getStatus() != null) {
            reservation.setStatus(reservationDetails.getStatus());
        }
        if (reservationDetails.getTotalPrice() != null) {
            reservation.setTotalPrice(reservationDetails.getTotalPrice());
        }
        if (reservationDetails.getHotel() != null) {
            reservation.setHotel(reservationDetails.getHotel());
        }
        if (reservationDetails.getRoomType() != null) {
            reservation.setRoomType(reservationDetails.getRoomType());
        }
        if (reservationDetails.getUser() != null) {
            reservation.setUser(reservationDetails.getUser());
        }

        validateReservation(reservation);
        return reservationRepository.save(reservation);
    }

    public void deleteReservation(UUID id) {
        if (!reservationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Reservation", "id", id);
        }
        reservationRepository.deleteById(id);
    }

    private void validateReservation(Reservation reservation) {
        if (reservation.getCheckInDate() == null) {
            throw new BadRequestException("Check-in date cannot be null");
        }
        if (reservation.getCheckOutDate() == null) {
            throw new BadRequestException("Check-out date cannot be null");
        }
        if (reservation.getCheckInDate().isAfter(reservation.getCheckOutDate())) {
            throw new BadRequestException("Check-in date cannot be after check-out date");
        }
        if (reservation.getCheckInDate().isBefore(LocalDate.now())) {
            throw new BadRequestException("Check-in date cannot be in the past");
        }
        if (reservation.getStatus() == null) {
            throw new BadRequestException("Status cannot be null");
        }
        if (reservation.getTotalPrice() == null || reservation.getTotalPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BadRequestException("Total price must be greater than 0");
        }
        if (reservation.getHotel() == null) {
            throw new BadRequestException("Hotel cannot be null");
        }
        if (reservation.getRoomType() == null) {
            throw new BadRequestException("Room type cannot be null");
        }
        if (reservation.getUser() == null) {
            throw new BadRequestException("User cannot be null");
        }
    }
} 