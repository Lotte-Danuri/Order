package com.lotte.danuri.order.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "order_header")
public class OrderHeader extends BaseEntity{

    private String buyerId;

    private Double totalPrice;

    private Long totalQuantity;

    private String address1;

    private String address2;

    private Date orderDate;

    @JsonBackReference
    @OneToMany(mappedBy = "orderHeader",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderData> orderDatas;
}
