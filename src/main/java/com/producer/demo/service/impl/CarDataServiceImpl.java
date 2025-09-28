package com.producer.demo.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.producer.demo.dto.CarDto;
import com.producer.demo.persistence.RedisRepository;
import com.producer.demo.service.CarDataService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;

@Service
@AllArgsConstructor
public class CarDataServiceImpl implements CarDataService {
    private StringRedisTemplate template;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void saveCarData(String key, CarDto value) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(value);
        template.opsForValue().set(key, json);
    }

    public CarDto getCarData(String key) throws Exception {
        String json = template.opsForValue().get(key);
        return json != null ? objectMapper.readValue(json, CarDto.class) : null;
    }
}
