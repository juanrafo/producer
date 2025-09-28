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

    public CarDto getCarById(Long id) throws Exception {
        CarEntity carEntity = carRepository.findById(id)
                .orElseThrow(() -> new Exception("Car not found with id: " + id));
        return toMap(carEntity);
    }

    public CarDto add(CarDto carDto) {
        CarEntity carEntity = new CarEntity();
        carEntity.setBrand(carDto.getBrand());
        carEntity.setModel(carDto.getModel());
        carEntity.setYear(carDto.getYear());
        CarEntity carEntityNew = carRepository.save(carEntity);
        return CarDto.builder()
                .id(carEntityNew.getId())
                .brand(carEntityNew.getBrand())
                .model(carEntityNew.getModel())
                .year(carEntityNew.getYear())
                .build();
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
