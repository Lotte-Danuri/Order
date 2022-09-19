package com.lotte.danuri.order.model.dto;

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
}
