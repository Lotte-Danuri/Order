package com.lotte.danuri.order.service.order;

import com.lotte.danuri.order.client.ProductServiceClient;
import com.lotte.danuri.order.error.ErrorCode;
import com.lotte.danuri.order.exception.OrderNotFoundException;
import com.lotte.danuri.order.model.dto.OrderDataDto;
import com.lotte.danuri.order.model.dto.OrderHeaderDto;
import com.lotte.danuri.order.model.dto.client.ProductDto;
import com.lotte.danuri.order.model.dto.request.ProductListDto;
import com.lotte.danuri.order.model.entity.OrderData;
import com.lotte.danuri.order.model.entity.OrderHeader;
import com.lotte.danuri.order.repository.OrderDataRepository;
import com.lotte.danuri.order.repository.OrderHeaderRepository;
import com.lotte.danuri.order.service.messagequeue.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService{

    private final OrderHeaderRepository orderHeaderRepository;
    private final OrderDataRepository orderDataRepository;
    private final KafkaProducerService kafkaProducerService;
    private final ProductServiceClient productServiceClient;
    private final CircuitBreakerFactory circuitBreakerFactory;

    @Override
    public void createOrder(OrderHeaderDto orderHeaderDto){
        // Todo : 상품 수량 체크 해야함
        // Todo : 상품 존재 여부 체크 해야함

        // OrderHeader INSERT
        OrderHeader orderHeader = orderHeaderRepository.save(
                OrderHeader.builder()
                        .buyerId(orderHeaderDto.getBuyerId())
                        .totalPrice(orderHeaderDto.getTotalPrice())
                        .totalQuantity(orderHeaderDto.getTotalQuantity())
                        .address1(orderHeaderDto.getAddress1())
                        .address2(orderHeaderDto.getAddress2())
                        .orderDate(LocalDateTime.now())
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
            kafkaProducerService.send("order-insert",v);
        });
        kafkaProducerService.send("order-insert-cart-delete",orderHeaderDto);
        orderDataRepository.saveAll(orderDataList);
    }

    @Override
    public List<OrderHeaderDto> getOrders(OrderHeaderDto orderHeaderDto){
        log.info("Before Retrieve [getOrders] Method IN [Order-Service]");
        List<OrderHeader> orderHeaders = orderHeaderRepository.findAllByMemberId(orderHeaderDto.getBuyerId());
        List<OrderHeaderDto> result = new ArrayList<>();
        // OrderData -> OrderDataDto
        orderHeaders.forEach(v -> {
            List<OrderDataDto> orderDataDtoList = new ArrayList<>();
            v.getOrderData().forEach(w -> {
                log.info("Before Call [getProduct] Method IN [Order-Service]");
                CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");
                ProductDto productDto = circuitBreaker.run(() -> productServiceClient.getProduct(w.getProductId()),
                        throwable -> new ProductDto());
                log.info("After Call [getProduct] Method IN [Order-Service]");
                OrderDataDto orderDataDto = new OrderDataDto(w, productDto.getThumbnailUrl(), productDto.getProductCode());
                orderDataDtoList.add(orderDataDto);
            });
            OrderHeaderDto orderHeaderDto_ = new OrderHeaderDto(v, orderDataDtoList);
            result.add(orderHeaderDto_);
        });
        Collections.reverse(result);
        log.info("After Retrieve [getOrders] Method IN [Order-Service]");
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

    @Override
    public Long getOrdersPrice(OrderHeaderDto orderHeaderDto){
        log.info("Before Retrieve [getOrdersPrice] Method IN [Order-Service]");
        Long totalPrice = orderHeaderRepository.findTotalPriceByMemberId(orderHeaderDto.getBuyerId());
        log.info("After Retrieve [getOrdersPrice] Method IN [Order-Service]");
        return totalPrice;
    }

    @Override
    public List<Long> getOrdersCount(ProductListDto productListDto){
        log.info("Before Retrieve [getOrdersCount] Method IN [Product-Service]");
        List<Long> result = new ArrayList<>();
        productListDto.getProductId().forEach(v -> {
            result.add(orderDataRepository.countByProductId(v));
        });
        log.info("After Retrieve [getOrdersCount] Method IN [Product-Service]");
        return result;
    }

    @Override
    public List<Long> getOrdersCountByDate(ProductListDto productListDto){
        log.info("Before Retrieve [getOrdersCountByDate] Method IN [Product-Service]");
        List<Long> result = new ArrayList<>();
        productListDto.getProductId().forEach(v -> {
            result.add(orderDataRepository.countByProductIdAndCreatedDateBetween(v,productListDto.getStartDate(),productListDto.getEndDate()));
        });
        log.info("After Retrieve [getOrdersCountByDate] Method IN [Product-Service]");
        return result;
    }
}