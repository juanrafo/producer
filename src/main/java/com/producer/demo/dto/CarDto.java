package com.producer.demo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CarDto {
    private Long id;
    private String brand;
    private String model;
    private Integer year;
}
