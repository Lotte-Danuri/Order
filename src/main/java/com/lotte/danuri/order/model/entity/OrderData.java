package com.lotte.danuri.order.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Builder
@Table(name = "order_data")
public class OrderData extends BaseEntity {

    private String sellerId;
    private Long productId;
    private String productName;
    private Long productQuantity;
    private Double productPrice;

    private LocalDateTime deletedDate;

    @ManyToOne
    @JoinColumn(name = "OrderHeader_id")
    @JsonBackReference
    private OrderHeader orderHeader;
}
