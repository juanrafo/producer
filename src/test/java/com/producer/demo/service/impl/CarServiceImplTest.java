package com.producer.demo.service.impl;

import com.producer.demo.dto.CarDto;
import com.producer.demo.persistence.CarRepository;
import com.producer.demo.persistence.entity.CarEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarServiceImplTest {

    @InjectMocks
    public CarServiceImpl carService;

    @Mock
    public CarRepository carRepository;

    @Test
    public void testGetAllCars() {
        // Implement test logic here

        when(carRepository.findAll()).thenReturn(List.of(
                new CarEntity(1L, "Toyota", "Camry", 2020),
                new CarEntity(2L, "Honda", "Civic", 2019)
        ));
        List<CarDto> carDtoList= carService.getAllCars();
        assertNotNull(carDtoList);
    }
}