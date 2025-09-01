package com.producer.demo.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import com.producer.demo.kafka.CarProducer;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producer")
@AllArgsConstructor
public class ProducerController {

    private final CarProducer carProducer;

    @PostMapping("/car")
    @ResponseStatus(HttpStatus.OK)
    public String produceCar(@RequestBody String carData) {
        carProducer.sendCar(carData);
        return "Car producido correctamente";
    }
}
