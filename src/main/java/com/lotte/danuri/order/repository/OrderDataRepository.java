package com.lotte.danuri.order.repository;

import com.lotte.danuri.order.model.entity.OrderData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDataRepository extends JpaRepository<OrderData, Long> {

    Long countByProductId(Long v);
}
