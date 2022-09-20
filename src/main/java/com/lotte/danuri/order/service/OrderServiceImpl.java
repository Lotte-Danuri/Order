package com.lotte.danuri.order.service;

import com.lotte.danuri.order.error.ErrorCode;
import com.lotte.danuri.order.exception.OrderNotFoundException;
import com.lotte.danuri.order.model.dto.OrderDataDto;
import com.lotte.danuri.order.model.dto.OrderHeaderDto;
import com.lotte.danuri.order.model.entity.OrderData;
import com.lotte.danuri.order.model.entity.OrderHeader;
import com.lotte.danuri.order.repository.OrderDataRepository;
import com.lotte.danuri.order.repository.OrderHeaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public List<OrderHeaderDto> getOrders(String memberId){
        List<OrderHeader> orderHeaders = orderHeaderRepository.findAllByMemberId(memberId);
        List<OrderHeaderDto> result = new ArrayList<>();

        // OrderData -> OrderDataDto
        orderHeaders.forEach(v -> {
            List<OrderDataDto> orderDataDtoList = new ArrayList<>();
            v.getOrderData().forEach(w -> {
                OrderDataDto orderDataDto = new OrderDataDto(w);
                orderDataDtoList.add(orderDataDto);
            });
            OrderHeaderDto orderHeaderDto = new OrderHeaderDto(v, orderDataDtoList);
            result.add(orderHeaderDto);
        });
        return result;
    }

    @Override
    public void deleteOrder(OrderHeaderDto orderHeaderDto){
        Optional<OrderHeader> orderHeader = orderHeaderRepository.findById(orderHeaderDto.getId());

        // 예외 처리
        // 1. 주문이 존재하지 않을 경우
        if(orderHeader.isEmpty()) {
            throw new OrderNotFoundException("Order not present in the database", ErrorCode.ORDER_NOT_FOUND);
        }

        // OrderHeader 삭제
        orderHeader.get().updateDeletedDate(LocalDateTime.now());
        orderHeaderRepository.save(orderHeader.get());

        // OrderData 삭제
        orderHeader.get().getOrderData().forEach(v -> {
            v.updateDeletedDate(LocalDateTime.now());
        });

        orderDataRepository.saveAll(orderHeader.get().getOrderData());
    }
}
