package com.producer.demo.service.impl;

import com.producer.demo.dto.CarDto;
import com.producer.demo.persistence.CarRepository;
import com.producer.demo.persistence.entity.CarEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarServiceImplTest {

    @InjectMocks
    public CarServiceImpl carService;

    @Mock
    public CarRepository carRepository;

    @Test
    void testGetAllCars() {
        // Implement test logic here

        when(carRepository.findAll()).thenReturn(List.of(
                new CarEntity(1L, "Toyota", "Camry", 2020),
                new CarEntity(2L, "Honda", "Civic", 2019)
        ));
        List<CarDto> carDtoList= carService.getAllCars();
        assertNotNull(carDtoList);
    }

    @Test
    @DisplayName("Test get car by id")
    void whenGetCarByIdeReturnSuccessfully() {
        when(carRepository.findById(1L))
                .thenReturn(java.util.Optional.of(
                        new CarEntity(1L, "Toyota", "Camry", 2020)
        ));

        CarDto carDto= carService.getCarById(1L);
        assertNotNull(carDto);
    }


    @Test
    @DisplayName("Test get car by id is null")
    void whenGetCarByIdReturnNull() {
        when(carRepository.findById(1L))
                .thenReturn(java.util.Optional.empty());

        CarDto carDto= carService.getCarById(1L);
        assertNull(carDto);
    }

    @Test
    @DisplayName("Test add car successfully")
    void whenAddCarReturnSuccessfully() {
        CarDto carDto = CarDto.builder()
                .brand("Toyota")
                .model("Camry")
                .year(2020)
                .build();

        when(carRepository.save(any(CarEntity.class)))
                .thenReturn(new CarEntity(1L, "Toyota", "Camry", 2020));

        CarDto carDtoResult= carService.add(carDto);
        assertNotNull(carDtoResult);
        assertEquals(1L, carDtoResult.getId());

    }
}