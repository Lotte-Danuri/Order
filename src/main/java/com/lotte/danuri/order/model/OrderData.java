package com.lotte.danuri.order.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "order_data")
public class OrderData extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "OrderData_id")
    private OrderHeader orderHeader;

    private String sellerId;

    private Long productId;

    private String productName;

    private Long productQuantity;

    private Double productPrice;
}
