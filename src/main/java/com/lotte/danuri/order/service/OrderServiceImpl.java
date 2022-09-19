package com.lotte.danuri.order.service;

import com.lotte.danuri.order.model.dto.OrderHeaderDto;
import com.lotte.danuri.order.model.entity.OrderData;
import com.lotte.danuri.order.model.entity.OrderHeader;
import com.lotte.danuri.order.repository.OrderDataRepository;
import com.lotte.danuri.order.repository.OrderHeaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderHeaderRepository orderHeaderRepository;
    private final OrderDataRepository orderDataRepository;

    @Override
    public void createOrder(OrderHeaderDto orderHeaderDto){
        // 상품 수량 체크 해야함
        // 상품 존재 여부 체크 해야함
        // 주문 시 상품 수량 감소 해야함

        // OrderHeader INSERT
        OrderHeader orderHeader = orderHeaderRepository.save(
                OrderHeader.builder()
                        .buyerId(orderHeaderDto.getBuyerId())
                        .totalPrice(orderHeaderDto.getTotalPrice())
                        .totalQuantity(orderHeaderDto.getTotalQuantity())
                        .address1(orderHeaderDto.getAddress1())
                        .address2(orderHeaderDto.getAddress2())
                        .orderDate(orderHeaderDto.getOrderDate())
                        .build()
        );

        // OrderData INSERT
        List<OrderData> orderDataList = new ArrayList<>();
        orderHeaderDto.getOrderDataDtoList().forEach( v -> {
            OrderData orderData = OrderData.builder()
                    .sellerId(v.getSellerId())
                    .productId(v.getProductId())
                    .productName(v.getProductName())
                    .productQuantity(v.getProductQuantity())
                    .productPrice(v.getProductPrice())
                    .orderHeader(orderHeader)
                    .build();
            orderDataList.add(orderData);
        });
        orderDataRepository.saveAll(orderDataList);
    }
}
