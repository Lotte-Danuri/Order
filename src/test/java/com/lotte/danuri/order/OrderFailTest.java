package com.lotte.danuri.order;

import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class OrderFailTest {
    @Autowired
    ObjectMapper mapper;

    @Autowired
    MockMvc mvc;

    private static final Long id = 100L;
    private static final String BASE_URL = "/orders";
    private static final String buyerId = "3";
    private static final Double totalPrice = 1000000D;
    private static final Long totalQuantity = 5L;
    private static final String address1 = "Seoul";
    private static final String address2 = "Dang-san";
    private static final LocalDateTime orderDate = LocalDateTime.now();

    @Test
    @DisplayName("주문 삭제 실패 테스트 (주문 미존재)")
    void delete_order_fail_test_by_no_order() throws Exception {

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
                .andExpect(status().isBadRequest());
    }
}
