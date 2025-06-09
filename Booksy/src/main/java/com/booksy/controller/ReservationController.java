package com.booksy.controller;

import com.booksy.entity.Reservation;
import com.booksy.service.ReservationService;
import com.booksy.dto.ReservationDTO;
import com.booksy.entity.User;
import com.booksy.entity.Hotel;
import com.booksy.entity.RoomType;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public List<ReservationDTO> getAllReservations() {
        return reservationService.getAllReservations().stream().map(this::toDTO).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable UUID id) {
        Reservation reservation = reservationService.getReservationById(id);
        return ResponseEntity.ok(reservation);
    }

    @PostMapping
    public ReservationDTO createReservation(@Valid @RequestBody ReservationDTO reservationDTO) {
        Reservation reservation = toEntity(reservationDTO);
        return toDTO(reservationService.saveReservation(reservation));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationDTO> updateReservation(@PathVariable UUID id, @Valid @RequestBody ReservationDTO reservationDTO) {
        Reservation updated = reservationService.updateReservation(id, toEntity(reservationDTO));
        if (updated != null) {
            return ResponseEntity.ok(toDTO(updated));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable UUID id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }

    // Mapping methods
    private ReservationDTO toDTO(Reservation reservation) {
        ReservationDTO dto = new ReservationDTO();
        if (reservation.getUser() != null) dto.setUserId(reservation.getUser().getUserId());
        if (reservation.getHotel() != null) dto.setHotelId(reservation.getHotel().getHotelId());
        if (reservation.getRoomType() != null) dto.setRoomTypeId(reservation.getRoomType().getRoomTypeId());
        dto.setCheckInDate(reservation.getCheckInDate());
        dto.setCheckOutDate(reservation.getCheckOutDate());
        dto.setTotalPrice(reservation.getTotalPrice());
        dto.setStatus(reservation.getStatus());
        return dto;
    }

    private Reservation toEntity(ReservationDTO dto) {
        Reservation reservation = new Reservation();
        if (dto.getUserId() != null) {
            User user = new User();
            user.setUserId(dto.getUserId());
            reservation.setUser(user);
        }
        if (dto.getHotelId() != null) {
            Hotel hotel = new Hotel();
            hotel.setHotelId(dto.getHotelId());
            reservation.setHotel(hotel);
        }
        if (dto.getRoomTypeId() != null) {
            RoomType roomType = new RoomType();
            roomType.setRoomTypeId(dto.getRoomTypeId());
            reservation.setRoomType(roomType);
        }
        reservation.setCheckInDate(dto.getCheckInDate());
        reservation.setCheckOutDate(dto.getCheckOutDate());
        reservation.setTotalPrice(dto.getTotalPrice());
        reservation.setStatus(dto.getStatus());
        return reservation;
    }
} 