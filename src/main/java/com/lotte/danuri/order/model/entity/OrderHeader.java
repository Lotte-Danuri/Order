package com.lotte.danuri.order.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Builder
@Table(name = "order_header")
public class OrderHeader extends BaseEntity {

    private String buyerId;
    private Double totalPrice;
    private Long totalQuantity;
    private String address1;
    private String address2;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime orderDate;

    private LocalDateTime deletedDate;

    @JsonManagedReference
    @OneToMany(mappedBy = "orderHeader",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderData> orderData;

    public void updateDeletedDate(LocalDateTime now) {
        this.deletedDate = now;
    }
}
