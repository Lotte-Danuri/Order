package com.lotte.danuri.order.model.dto;

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
}
