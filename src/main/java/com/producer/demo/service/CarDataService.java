package com.producer.demo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.producer.demo.dto.CarDto;

public interface CarDataService {

    void saveCarData(String key, CarDto value) throws JsonProcessingException;

}
