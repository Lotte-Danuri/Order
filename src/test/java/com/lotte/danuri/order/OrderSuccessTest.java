package com.lotte.danuri.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lotte.danuri.order.model.dto.OrderDataDto;
import com.lotte.danuri.order.model.dto.OrderHeaderDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class OrderSuccessTest {

    @Autowired
    ObjectMapper mapper;

    @Autowired
    MockMvc mvc;

    private static final String BASE_URL = "/orders";
    private static final Long id = 8L;
    private static final String buyerId = "3";
    private static final Double totalPrice = 1000000D;
    private static final Long totalQuantity = 5L;
    private static final String address1 = "Seoul";
    private static final String address2 = "Dang-san";
    private static final LocalDateTime orderDate = LocalDateTime.now();

    @Test
    @DisplayName("주문 생성 테스트")
    void create_order_test() throws Exception {

        List<OrderDataDto> orderDataDtoList = new ArrayList<>();

        OrderDataDto orderDataDto_o1 = OrderDataDto.builder()
                        .sellerId("1")
                        .productId(43L)
                        .productName("테일러드 카라 싱글 버튼 자켓")
                        .productQuantity(10L)
                        .productPrice(2360000D)
                        .build();

        OrderDataDto orderDataDto_o2 = OrderDataDto.builder()
                        .sellerId("2")
                        .productId(44L)
                        .productName("스모크 러플 엣지 롱 자켓")
                        .productQuantity(4L)
                        .productPrice(1790000D)
                        .build();

        orderDataDtoList.add(orderDataDto_o1);
        orderDataDtoList.add(orderDataDto_o2);

        String body = mapper.writeValueAsString(
                OrderHeaderDto.builder()
                        .buyerId(buyerId)
                        .totalPrice(totalPrice)
                        .totalQuantity(totalQuantity)
                        .address1(address1)
                        .address2(address2)
                        .orderDate(orderDate)
                        .orderDataDtoList(orderDataDtoList)
                        .build()
        );

        mvc.perform(post(BASE_URL + "/pays")
                        .header("memberId", "1")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("주문 조회 테스트")
    void read_order_test() throws Exception {

        mvc.perform(get(BASE_URL + "/pays/" + buyerId)
                        .header("memberId", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("주문 삭제 테스트")
    void delete_order_test() throws Exception {

        String body = mapper.writeValueAsString(
                OrderHeaderDto.builder()
                        .id(id)
                        .build()
        );

        mvc.perform(delete(BASE_URL + "/pays")
                        .header("memberId", "1")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }
}
