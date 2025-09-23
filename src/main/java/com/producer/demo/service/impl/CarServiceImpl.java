package com.producer.demo.service.impl;

import com.producer.demo.dto.CarDto;
//import com.producer.demo.mapper.CarDtoMapper;
import com.producer.demo.persistence.CarRepository;
import com.producer.demo.persistence.entity.CarEntity;
import com.producer.demo.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    //private final CarDtoMapper carDtoMapper;

    public List<CarDto> getAllCars() {
        return carRepository.findAll()
                .stream()
                .map(this::toMap)
                .toList();
    }

    private CarDto toMap(CarEntity carEntity){
        return CarDto.builder()
                .id(carEntity.getId())
                .brand(carEntity.getBrand())
                .model(carEntity.getModel())
                .year(carEntity.getYear())
                .build();
    }
}
