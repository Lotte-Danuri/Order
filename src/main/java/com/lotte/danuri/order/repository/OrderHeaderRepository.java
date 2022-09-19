package com.lotte.danuri.order.repository;

import com.lotte.danuri.order.model.entity.OrderData;
import com.lotte.danuri.order.model.entity.OrderHeader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderHeaderRepository extends JpaRepository<OrderHeader, Long> {

    List<OrderHeader> findAllByBuyerIdAndDeletedDateIsNull(String memberId);

    //List<OrderHeader> findAllByDeletedDateIsNull(Long memberId);
}
