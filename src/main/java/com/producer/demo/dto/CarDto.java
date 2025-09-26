package com.producer.demo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

@Builder
@Getter
@Setter
@RedisHash("Car")
public class CarDto {
    private Long id;
    private String brand;
    private String model;
    private Integer year;
}
