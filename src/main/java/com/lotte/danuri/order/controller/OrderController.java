package com.lotte.danuri.order.controller;

import com.lotte.danuri.order.model.dto.OrderHeaderDto;
import com.lotte.danuri.order.service.order.OrderService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/orders")
@RequiredArgsConstructor
@CrossOrigin("*")
public class OrderController {

    private final OrderService orderService;

    @PostMapping(value ="/pays", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "주문 생성", notes = "주문(결제)을 생성한다.")
    public ResponseEntity<?> createOrder (@RequestBody OrderHeaderDto orderHeaderDto){

        //TODO : 배송 완료 시 보증 시작일, 종료일 UPDATE
        orderService.createOrder(orderHeaderDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value ="/pays/list", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "주문 조회", notes = "한 사용자의 모든 주문을 조회한다.")
    public ResponseEntity<?> getOrders (@RequestBody OrderHeaderDto orderHeaderDto){

        List<OrderHeaderDto> orderHeaderDtoList = orderService.getOrders(orderHeaderDto);
        return ResponseEntity.ok(orderHeaderDtoList);
    }

    @DeleteMapping(value = "/pays", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "주문 삭제", notes = "한 사용자의 하나의 주문을 삭제한다.")
    public ResponseEntity deleteOrder(@RequestBody OrderHeaderDto orderHeaderDto){

        orderService.deleteOrder(orderHeaderDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value ="/pays/total", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "주문 총 금액 조회", notes = "한 사용자의 총 주문 금액을 조회한다.")
    public ResponseEntity<?> getOrdersPrice (@RequestBody OrderHeaderDto orderHeaderDto){

        Long totalPrice = orderService.getOrdersPrice(orderHeaderDto);
        return ResponseEntity.ok(totalPrice);
    }
}
