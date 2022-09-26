package com.lotte.danuri.order.controller;

import com.lotte.danuri.order.model.dto.OrderHeaderDto;
import com.lotte.danuri.order.service.OrderService;
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

        orderService.createOrder(orderHeaderDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value ="/pays/{memberId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "주문 조회", notes = "한 사용자의 모든 주문을 조회한다.")
    public ResponseEntity<?> getOrders (@PathVariable String memberId){

        List<OrderHeaderDto> orderHeaderDtoList = orderService.getOrders(memberId);
        return ResponseEntity.ok(orderHeaderDtoList);
    }

    @DeleteMapping(value = "/pays", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "주문 삭제", notes = "한 사용자의 하나의 주문을 삭제한다.")
    public ResponseEntity deleteOrder(@RequestBody OrderHeaderDto orderHeaderDto){

        orderService.deleteOrder(orderHeaderDto);
        return ResponseEntity.ok().build();
    }
}
