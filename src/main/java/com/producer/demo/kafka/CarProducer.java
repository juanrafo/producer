package com.producer.demo.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CarProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public CarProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendCar(String car) {
        kafkaTemplate.send("carstore-topic", car);
    }
}