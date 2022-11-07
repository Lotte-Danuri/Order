package com.lotte.danuri.order.model.dto;

import com.lotte.danuri.order.model.entity.OrderData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class OrderDataDto {

    private Long id;
    private String sellerId;
    private Long productId;
    private String productName;
    private String productCode;
    private Long productQuantity;
    private Long productPrice;
    private LocalDateTime warrantyStartDate;
    private LocalDateTime warrantyEndDate;
    private String thumbnail;
    public OrderDataDto(OrderData w, String thumbnail, String productCode) {
        this.id = w.getId();
        this.sellerId = w.getSellerId();
        this.productId = w.getProductId();
        this.productName = w.getProductName();
        this.productQuantity = w.getProductQuantity();
        this.productPrice = w.getProductPrice();
        this.productCode = productCode;
        this.thumbnail = thumbnail;
    }
}
