package com.producer.demo.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.producer.demo.dto.CarDto;
import com.producer.demo.service.CarDataService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class CarDataServiceImpl implements CarDataService {
    private StringRedisTemplate template;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void saveCarData(String key, CarDto value) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(value);
        template.opsForValue().set(key, json);
    }

    public CarDto getCarData(String key) throws Exception {
        log.info("Fetching car data from Redis with key: {}", key);
        String json = template.opsForValue().get(key);
        return json != null ? objectMapper.readValue(json, CarDto.class) : null;
    }
}
