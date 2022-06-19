package com.farukkavlak.loginsystem.Service;

import com.farukkavlak.loginsystem.Converter.CarMapper;
import com.farukkavlak.loginsystem.Dao.CarDao;
import com.farukkavlak.loginsystem.Dto.CarReturnDto;
import com.farukkavlak.loginsystem.Dto.CarSaveUpdateRequestDto;
import com.farukkavlak.loginsystem.Entity.Car;
import com.farukkavlak.loginsystem.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarDao carDao;
    //If is there a logged user and plate is valid, save the car
    public ResponseEntity saveCar(CarSaveUpdateRequestDto carSaveUpdateRequestDto,UserService userService) {
        CarMapper carMapper = new CarMapper();
        if(!isCarExist(carSaveUpdateRequestDto.getPlate())){
            Car car = carMapper.convertToCar(carSaveUpdateRequestDto);
            if(isPlateInvalid(car)){
               return new ResponseEntity("Plate is not valid",HttpStatus.BAD_REQUEST);
            }
            User user = userService.getLoggedUser();
            user.getCars().add(car);
            carDao.save(car);
            return new ResponseEntity<>("Car added", HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>("These car already added, you cannot add same car twice.", HttpStatus.BAD_REQUEST);
        }

    }

    //Get all cars of this brand and model
    public ResponseEntity getCarWithBrandAndModel(String brand, String model) {
        List<Car> cars = carDao.findByBrandAndModel(brand,model);
        CarMapper carMapper = new CarMapper();
        List<CarReturnDto> carReturnDtos = carMapper.convertToCarDtoList(cars);
        return new ResponseEntity(carReturnDtos,HttpStatus.OK);
    }

    //If logged user have the car with this plate, delete it
    public ResponseEntity deleteCarWithPlate(String plate,UserService userService) {
        if(isCarExist(plate)){
            User user = userService.getLoggedUser();
            Car car = carDao.getByPlate(plate);
            if(isUserHaveThisCar(user, car)){
                user.getCars().remove(car);
                carDao.delete(car);
                return new ResponseEntity("Car deleted",HttpStatus.OK);
            }
            return new ResponseEntity("You do not own a car with this plate number.",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("You do not own a car with this plate number.",HttpStatus.BAD_REQUEST);
    }
    //If logged user have the car with this plate, update the saved car with given info
    public ResponseEntity updateCarWithPlate(CarSaveUpdateRequestDto carSaveUpdateRequestDto,UserService userService) {
        if(isCarExist(carSaveUpdateRequestDto.getPlate())){
            User user = userService.getLoggedUser();
            Car car = carDao.getByPlate(carSaveUpdateRequestDto.getPlate());
            if(isUserHaveThisCar(user, car)){
                CarMapper carMapper = new CarMapper();
                car = carMapper.convertToCar(carSaveUpdateRequestDto,car);
                carDao.save(car);
                return new ResponseEntity("Car Updated",HttpStatus.OK);
            }
            return new ResponseEntity("You do not own a car with this plate number.",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("You do not own a car with this plate number.",HttpStatus.BAD_REQUEST);
    }


    /*
    Helper functions
    */
    private boolean isUserHaveThisCar(User user, Car car) {
        return user.getCars().contains(car);
    }

    private boolean isCarExist(String plate) {
        return carDao.existsByPlate(plate);
    }

    private boolean isPlateInvalid(Car car) {
        return car.getPlate().length() < 7
                || car.getPlate().length() > 8
                || car.getPlate().contains(" ")
                || isContainLowerCase(car.getPlate())
                || isContainTurkishLetter(car.getPlate());
    }
    private boolean isContainTurkishLetter(String plate) {
        char[] turkishLetters = {'Ç', 'Ğ', 'İ', 'Ö', 'Ş', 'Ü'};
        for (char letter : turkishLetters) {
            if (plate.indexOf(letter) != -1) {
                return true;
            }
        }
        return false;
    }

    private boolean isContainLowerCase(String plate) {
        for(int letterNo=0;letterNo<plate.length();letterNo++){
            if(plate.charAt(letterNo)>=97&&plate.charAt(letterNo)<=122){
                return true;
            }
        }
        return false;
    }


}
