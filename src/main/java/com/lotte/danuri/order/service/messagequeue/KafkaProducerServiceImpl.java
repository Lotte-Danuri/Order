package com.lotte.danuri.order.service.messagequeue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lotte.danuri.order.model.dto.OrderDataDto;
import com.lotte.danuri.order.model.dto.OrderHeaderDto;
import com.lotte.danuri.order.model.entity.OrderData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProducerServiceImpl implements KafkaProducerService{
    private final KafkaTemplate<String,String> kafkaTemplate;

    @Override
    public OrderDataDto send(String topic, OrderDataDto orderDataDto){
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "";
        try{
            jsonInString = mapper.writeValueAsString(orderDataDto);
        } catch(JsonProcessingException ex){
            ex.printStackTrace();
        }

        kafkaTemplate.send(topic, jsonInString);
        log.info("Kafka Producer sent data from the Order microservice" + orderDataDto);

        return orderDataDto;
    }

    @Override
    public OrderHeaderDto send(String topic, OrderHeaderDto orderHeaderDto){
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "";
        try{
            jsonInString = mapper.writeValueAsString(orderHeaderDto);
        } catch(JsonProcessingException ex){
            ex.printStackTrace();
        }

        kafkaTemplate.send(topic, jsonInString);
        log.info("Kafka Producer sent data from the Order microservice" + orderHeaderDto);

        return orderHeaderDto;
    }
}