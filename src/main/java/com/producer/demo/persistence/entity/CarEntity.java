package com.producer.demo.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Car")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    private Integer year;

}
