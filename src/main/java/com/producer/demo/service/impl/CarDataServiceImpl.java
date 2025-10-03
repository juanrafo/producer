package com.producer.demo.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.producer.demo.dto.CarDto;
import com.producer.demo.service.CarDataService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
@Slf4j
public class CarDataServiceImpl implements CarDataService {
    private StringRedisTemplate template;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final long TTL_SECONDS = 20;

    public void saveCarData(String key, CarDto value) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(value);
        template.opsForList().rightPush(key, json);
    }

    public CarDto getCarData(String key,Long id) {
        log.info("Fetching car data from Redis with key: {}", key);
        List<String> json = template.opsForList().range(key,0,-1);

        if (Objects.isNull(json))
            return null;
        var carDtoOptional =
        json.stream()
                .map(item-> {
                    try {
                        return objectMapper.readValue(item, CarDto.class);
                    } catch (JsonProcessingException e) {
                        return CarDto.builder().build();
                    }
                })
                .filter(carDto -> carDto.getId().equals(id))
                .findFirst();
        return carDtoOptional.orElse(null);
    }
}
