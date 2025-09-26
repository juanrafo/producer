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
        List<CarDto> cars = carService.getAllCars();
        return ResponseEntity.ok(cars);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> addCar(@RequestBody CarDto carDto) throws JsonProcessingException {
        // Lógica para agregar el coche (a implementar)
        carService.add(carDto);
        carDataService.saveCarData(CAR_KEY_PREFIX,carDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Coche agregado correctamente");
    }



}
