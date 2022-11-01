package com.lotte.danuri.order.service.messagequeue;

import com.lotte.danuri.order.model.dto.OrderDataDto;
import com.lotte.danuri.order.model.dto.OrderHeaderDto;

public interface KafkaProducerService {
    OrderDataDto send(String topic, OrderDataDto orderDataDto);
    OrderHeaderDto send(String topic, OrderHeaderDto orderHeaderDto);
}
