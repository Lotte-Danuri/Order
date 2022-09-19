package com.lotte.danuri.order.repository;

import com.lotte.danuri.order.model.entity.OrderData;
import com.lotte.danuri.order.model.entity.OrderHeader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderHeaderRepository extends JpaRepository<OrderHeader, Long> {

}
