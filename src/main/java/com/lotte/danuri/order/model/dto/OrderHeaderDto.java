package com.lotte.danuri.order.model.dto;

import com.lotte.danuri.order.model.entity.OrderHeader;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class OrderHeaderDto {

    // OrderHeader
    private Long id;
    private String buyerId;
    private Double totalPrice;
    private Long totalQuantity;
    private String address1;
    private String address2;
    private LocalDateTime orderDate;

    // OrderData
    List<OrderDataDto> orderDataDtoList;

    public OrderHeaderDto(OrderHeader v, List<OrderDataDto> orderDataDtoList) {
        this.id = v.getId();
        this.buyerId = v.getBuyerId();
        this.totalPrice = v.getTotalPrice();
        this.totalQuantity = v.getTotalQuantity();
        this.address1 = v.getAddress1();
        this.address2 = v.getAddress2();
        this.orderDate = v.getOrderDate();
        this.orderDataDtoList = orderDataDtoList;
    }
}
