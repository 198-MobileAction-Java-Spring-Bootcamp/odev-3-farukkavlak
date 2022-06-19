package com.farukkavlak.loginsystem.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="CarTable")
@RequiredArgsConstructor
public class Car {
    //ID generate in sequence(0,1,2...)
    @Id
    @SequenceGenerator(name = "car_seq",sequenceName = "car_seq")
    @GeneratedValue(generator = "car_seq")
    private Long id;

    @Column(name = "plate",nullable = false)
    private String plate;

    @Column(name = "brand",nullable = false)
    private String brand;

    @Column(name = "model",nullable = false)
    private String model;

}
