package com.producer.demo.dto;

import lombok.*;


@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {
    private Long id;
    private String brand;
    private String model;
    private Integer year;
}
