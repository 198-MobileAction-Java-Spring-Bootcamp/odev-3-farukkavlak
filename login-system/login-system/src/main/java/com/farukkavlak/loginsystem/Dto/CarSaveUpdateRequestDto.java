package com.farukkavlak.loginsystem.Dto;

import lombok.Data;

@Data
public class CarSaveUpdateRequestDto {
    private String plate;
    private String brand;
    private String model;
}
