package com.producer.demo.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.producer.demo.dto.CarDto;
import com.producer.demo.service.CarDataService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
@Slf4j
public class CarDataServiceImpl implements CarDataService {
    private StringRedisTemplate template;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void saveCarData(String key, CarDto value) throws JsonProcessingException {
        template.opsForList().rightPush(key, objectMapper.writeValueAsString(value));
    }

    public CarDto getCarData(String key, Long id) {
        log.info("Fetching car data from Redis with key: {}", key);
        return
                Objects.requireNonNull(template.opsForList().range(key, 0, -1))
                        .stream()
                .map(item -> {
                    try {
                        return objectMapper.readValue(item, CarDto.class);
                    } catch (JsonProcessingException e) {
                        return CarDto.builder().build();
                    }
                })
                .filter(carDto -> carDto.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
