package com.lotte.danuri.order.model.dto;

import com.lotte.danuri.order.model.entity.OrderData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class OrderDataDto {

    private Long id;
    private String sellerId;
    private Long productId;
    private String productName;
    private Long productQuantity;
    private Double productPrice;

    public OrderDataDto(OrderData w) {
        this.id = w.getId();
        this.sellerId = w.getSellerId();
        this.productId = w.getProductId();
        this.productName = w.getProductName();
        this.productQuantity = w.getProductQuantity();
        this.productPrice = w.getProductPrice();
    }
}
