package com.booksy.entity;


	import jakarta.persistence.*;
	import lombok.Data;
	import java.io.Serializable;
	import java.math.BigDecimal;
	import java.time.LocalDate;
	import java.time.LocalDateTime;

	  @Entity
	  @Table(name = "room_pricing")
	  @Data
	  @IdClass(RoomPricingId.class)
	  public class RoomPricing implements Serializable {
	      @Id
	      @ManyToOne
	      @JoinColumn(name = "room_type_id")
	      private RoomType roomType;

	      @Id
	      private LocalDate date;

	      private BigDecimal price;

	      @Column(name = "created_at")
	      private LocalDateTime createdAt;

	      private static final long serialVersionUID = 1L;
	  }