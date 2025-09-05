package com.carbackend.domain;

import com.carbackend.dto.CarDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="car")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Car {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String brand;
    private String model;
    private String color;
    private String registrationNumber;
    private Integer modelYear;
    private Integer price;


    public void updateCar(CarDto carDto) {
        this.brand = carDto.getBrand();
        this.model = carDto.getModel();
        this.color = carDto.getColor();
        this.registrationNumber = carDto.getRegistrationNumber();
        this.modelYear = carDto.getModelYear();
        this.price = carDto.getPrice();

    }
}
