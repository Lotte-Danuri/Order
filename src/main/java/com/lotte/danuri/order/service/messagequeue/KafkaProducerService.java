package com.lotte.danuri.order.service.messagequeue;

import com.lotte.danuri.order.model.dto.OrderDataDto;

public interface KafkaProducerService {
    OrderDataDto send(String topic, OrderDataDto orderDataDto);
}
