package com.booksy.entity;
	import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

	@Entity
	@Table(name = "reservations")
	@Data
	public class Reservation {
	    @Id
	    @Column(name = "reservation_id")
	    private UUID reservationId;

		@ManyToOne
	    @JoinColumn(name = "user_id")
	    private User user;

	    @ManyToOne
	    @JoinColumn(name = "hotel_id")
	    private Hotel hotel;

	    @ManyToOne
	    @JoinColumn(name = "room_type_id")
	    private RoomType roomType;

	    @Column(name = "check_in_date")
	    private LocalDate checkInDate;

	    @Column(name = "check_out_date")
	    private LocalDate checkOutDate;

	    @Column(name = "total_price")
	    private BigDecimal totalPrice;

	    private String status;

	    @Column(name = "created_at")
	    private LocalDateTime createdAt;
	}
