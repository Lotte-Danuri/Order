package com.lotte.danuri.order.service.order;

import com.lotte.danuri.order.model.dto.OrderHeaderDto;
import com.lotte.danuri.order.model.dto.request.ProductListDto;

import java.util.List;


public interface OrderService {
    void createOrder(OrderHeaderDto orderHeaderDto);

    List<OrderHeaderDto> getOrders(OrderHeaderDto orderHeaderDto);

    void deleteOrder(OrderHeaderDto orderHeaderDto);

    Long getOrdersPrice(OrderHeaderDto orderHeaderDto);

    List<Long> getOrdersCount(ProductListDto productListDto);
}
