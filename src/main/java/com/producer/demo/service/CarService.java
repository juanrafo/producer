package com.producer.demo.service;

import com.producer.demo.dto.CarDto;

import java.util.List;

public interface CarService {
    List<CarDto> getAllCars();
    CarDto add(CarDto carDto);
    CarDto getCarById(Long id);
}
