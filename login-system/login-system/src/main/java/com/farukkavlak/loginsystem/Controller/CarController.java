package com.farukkavlak.loginsystem.Controller;

import com.farukkavlak.loginsystem.Dto.CarSaveUpdateRequestDto;
import com.farukkavlak.loginsystem.Service.CarService;
import com.farukkavlak.loginsystem.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/car")
@CrossOrigin
public class CarController {
    private final CarService carService;
    private final UserService userService;
    public CarController(CarService carService,UserService userService) {
        this.userService = userService;
        this.carService = carService;
    }

    @PostMapping("/saveCar")
    public ResponseEntity saveCar(@RequestBody CarSaveUpdateRequestDto carSaveUpdateRequestDto){
        return carService.saveCar(carSaveUpdateRequestDto,userService);
    }
    @GetMapping("/getCarWithBrandAndModel/{brandName}/{modelName}")
    public ResponseEntity getCarWithBrandAndModel(@PathVariable("brandName") String brand_name,@PathVariable("modelName") String model_name){
        return carService.getCarWithBrandAndModel(brand_name,model_name);
    }
    @DeleteMapping("/deleteCarWithPlate/{plate}")
    public ResponseEntity deleteCarWithPlate(@PathVariable("plate") String plate){
        return carService.deleteCarWithPlate(plate,userService);
    }
    @PutMapping("/update")
    public ResponseEntity updateCarWithPlate(@RequestBody CarSaveUpdateRequestDto carSaveUpdateRequestDto){
        return carService.updateCarWithPlate(carSaveUpdateRequestDto,userService);
    }



}
