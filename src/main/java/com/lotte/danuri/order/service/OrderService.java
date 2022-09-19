package com.lotte.danuri.order.service;

import com.lotte.danuri.order.model.dto.OrderHeaderDto;

public interface OrderService {
    void createOrder(OrderHeaderDto orderHeaderDto);
}
