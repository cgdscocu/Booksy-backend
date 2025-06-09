package com.booksy.repository;

import com.booksy.entity.RoomPricing;
import com.booksy.entity.RoomPricingId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomPricingRepository extends JpaRepository<RoomPricing, RoomPricingId> {
}