package com.lotte.danuri.order.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

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
    private Long productPrice;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime warrantyStartDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime warrantyEndDate;
    private LocalDateTime deletedDate;

    @ManyToOne
    @JoinColumn(name = "OrderHeader_id")
    @JsonBackReference
    private OrderHeader orderHeader;

    public void updateDeletedDate(LocalDateTime now) {
        this.deletedDate = now;
    }
}
