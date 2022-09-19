package com.lotte.danuri.order.controller;

import com.lotte.danuri.order.model.dto.OrderHeaderDto;
import com.lotte.danuri.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/orders")
@RequiredArgsConstructor
@CrossOrigin("*")
public class OrderController {

    private final OrderService orderService;

    @PostMapping(value="/pays", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> createOrder (@RequestBody OrderHeaderDto orderHeaderDto){
        orderService.createOrder(orderHeaderDto);
        return ResponseEntity.ok().build();
    }
}
