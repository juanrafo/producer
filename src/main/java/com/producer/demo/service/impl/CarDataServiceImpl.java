package com.producer.demo.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.producer.demo.dto.CarDto;
import com.producer.demo.persistence.RedisRepository;
import com.producer.demo.service.CarDataService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CarDataServiceImpl implements CarDataService {
    private final RedisRepository redisRepository;

    public void saveCarData(String key, CarDto value) throws JsonProcessingException {
        redisRepository.save(value);
    }

}
