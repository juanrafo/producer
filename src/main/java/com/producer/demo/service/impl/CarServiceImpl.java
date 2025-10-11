package com.producer.demo.service.impl;

import com.producer.demo.dto.CarDto;
import com.producer.demo.persistence.CarRepository;
import com.producer.demo.persistence.entity.CarEntity;
import com.producer.demo.service.CarService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@AllArgsConstructor
@Service
@Slf4j
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    public List<CarDto> getAllCars() {
        log.info("Fetching all car data from Database");
        return carRepository.findAll()
                .stream()
                .map(this::toMap)
                .toList();
    }

    public CarDto getCarById(Long id) {
        log.info("Fetching car data from Database with id: {}", id);
        return carRepository
                .findById(id)
                .map(this::toMap)
                .orElse(null);
    }

    public CarDto add(CarDto carDto) {
        return toMap(carRepository.save(toEntity(carDto)));
    }

    private CarDto toMap(CarEntity carEntity) {
        return CarDto.builder()
                .id(carEntity.getId())
                .brand(carEntity.getBrand())
                .model(carEntity.getModel())
                .year(carEntity.getYear())
                .build();
    }

    private CarEntity toEntity(CarDto carDto) {
        return CarEntity.builder()
                .brand(carDto.getBrand())
                .model(carDto.getModel())
                .year(carDto.getYear())
                .build();
    }
}
