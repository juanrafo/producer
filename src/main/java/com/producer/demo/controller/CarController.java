package com.producer.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.producer.demo.dto.CarDto;
import com.producer.demo.service.CarDataService;
import com.producer.demo.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

import static com.producer.demo.constants.Constants.CAR_KEY_PREFIX;

@RestController
@RequestMapping("/car")
@AllArgsConstructor
public class CarController {
    private final CarService carService;
    private final CarDataService carDataService;


    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<CarDto>> getAllCars() {
        return ResponseEntity.ok(carService.getAllCars());
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> addCar(@RequestBody CarDto carDto) throws JsonProcessingException {
        carDataService.saveCarData(CAR_KEY_PREFIX, carService.add(carDto));
        return ResponseEntity.status(HttpStatus.CREATED).body("Coche agregado correctamente");
    }

    @GetMapping("/find/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CarDto> getCar(@PathVariable Long id) {
        final CarDto carFromRedis = carDataService.getCarData(CAR_KEY_PREFIX, id);
        if (!Objects.isNull(carFromRedis)) {
            return ResponseEntity.ok(carFromRedis);
        }
        final CarDto carDto = carService.getCarById(id);
        if (Objects.isNull(carDto)) {
            return ResponseEntity.status(HttpStatus.OK).body(CarDto.builder().build());
        }
        return ResponseEntity.ok(carDto);
    }
}