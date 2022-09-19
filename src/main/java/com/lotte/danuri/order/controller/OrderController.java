package com.lotte.danuri.order.controller;

import com.lotte.danuri.order.model.dto.OrderHeaderDto;
import com.lotte.danuri.order.service.OrderService;
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
    public ResponseEntity<?> createOrder (@RequestBody OrderHeaderDto orderHeaderDto){

        orderService.createOrder(orderHeaderDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value ="/pays/{memberId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getOrders (@PathVariable String memberId){

        List<OrderHeaderDto> orderHeaderDtoList = orderService.getOrders(memberId);
        return ResponseEntity.ok(orderHeaderDtoList);
    }

    @DeleteMapping(value = "/pays", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity deleteOrder(@RequestBody OrderHeaderDto orderHeaderDto){

        orderService.deleteOrder(orderHeaderDto);
        return ResponseEntity.ok().build();
    }
}
