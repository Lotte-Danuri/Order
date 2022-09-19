package com.lotte.danuri.order.repository;

import com.lotte.danuri.order.model.entity.OrderData;
import com.lotte.danuri.order.model.entity.OrderHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderHeaderRepository extends JpaRepository<OrderHeader, Long> {

    @Query(
            value = "SELECT distinct oh FROM OrderHeader oh JOIN FETCH oh.orderData od "
                    + "WHERE oh.buyerId = :memberId "
                    + "AND od.orderHeader.buyerId = :memberId "
                    + "AND oh.deletedDate is null "
                    + "AND od.deletedDate is null "
    )
    List<OrderHeader> findAllByMemberId(String memberId);
}
