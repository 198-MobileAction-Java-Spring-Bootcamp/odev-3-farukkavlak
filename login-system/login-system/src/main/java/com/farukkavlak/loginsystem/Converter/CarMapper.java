package com.farukkavlak.loginsystem.Converter;

import com.farukkavlak.loginsystem.Dto.CarReturnDto;
import com.farukkavlak.loginsystem.Dto.CarSaveUpdateRequestDto;
import com.farukkavlak.loginsystem.Entity.Car;

import java.util.ArrayList;
import java.util.List;

public class CarMapper {
    //CarSaveUpdateRequestDto -> Car
    //Create new car to save command
    public Car convertToCar(CarSaveUpdateRequestDto carSaveUpdateRequestDto){
        Car car = new Car();
        car.setBrand(carSaveUpdateRequestDto.getBrand());
        car.setModel(carSaveUpdateRequestDto.getModel());
        car.setPlate(carSaveUpdateRequestDto.getPlate());
        return car;
    }
    //CarSaveUpdateRequestDto -> Car
    //Use exist car
    public Car convertToCar(CarSaveUpdateRequestDto carSaveUpdateRequestDto,Car car){
        car.setBrand(carSaveUpdateRequestDto.getBrand());
        car.setModel(carSaveUpdateRequestDto.getModel());
        car.setPlate(carSaveUpdateRequestDto.getPlate());
        return car;
    }
    //Car -> CarReturnDto
    public CarReturnDto convertToCarDto(Car car){
        CarReturnDto carReturnDto = new CarReturnDto();
        carReturnDto.setBrand(car.getBrand());
        carReturnDto.setPlate(car.getPlate());
        carReturnDto.setModel(car.getModel());
        return carReturnDto;
    }

    public List<CarReturnDto> convertToCarDtoList(List<Car> cars) {
        List<CarReturnDto> carReturnDtos = new ArrayList<>();
        for (Car car : cars) {
            carReturnDtos.add(this.convertToCarDto(car));
        }
        return carReturnDtos;
    }
}
