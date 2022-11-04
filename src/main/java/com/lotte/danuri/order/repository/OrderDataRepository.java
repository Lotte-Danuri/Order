package com.lotte.danuri.order.repository;

import com.lotte.danuri.order.model.entity.OrderData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface OrderDataRepository extends JpaRepository<OrderData, Long> {

    Long countByProductId(Long v);

    Long countByProductIdAndCreatedDateBetween(Long v, LocalDateTime startDate, LocalDateTime endDate);
}
