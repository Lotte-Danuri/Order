package com.lotte.danuri.order.service;

import com.lotte.danuri.order.model.dto.OrderHeaderDto;

import java.util.List;

public interface OrderService {
    void createOrder(OrderHeaderDto orderHeaderDto);

    List<OrderHeaderDto> getOrders(String memberId);

    void deleteOrder(OrderHeaderDto orderHeaderDto);
}
