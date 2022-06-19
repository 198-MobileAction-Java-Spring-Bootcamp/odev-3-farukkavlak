package com.farukkavlak.loginsystem.Controller;

import com.farukkavlak.loginsystem.Dto.UserChangePasswordDto;
import com.farukkavlak.loginsystem.Dto.UserLoginDto;
import com.farukkavlak.loginsystem.Dto.UserSaveRequestDto;
import com.farukkavlak.loginsystem.Service.CarService;
import com.farukkavlak.loginsystem.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class UserController {
    private final UserService userService;
    private final CarService carService;

    public UserController(UserService userService,CarService carService) {
        this.userService = userService;
        this.carService = carService;
    }
    //Get user login info with dto and login - authenticate
    @PostMapping("/signin")
    public ResponseEntity authenticateUser(@RequestBody UserLoginDto userLoginDto){
        return userService.loginUser(userLoginDto);
    }

    //Get signup info with dto and register user
    @PostMapping("/signup")
    public ResponseEntity registerUser(@RequestBody UserSaveRequestDto userSaveRequestDto){
        return userService.saveUser(userSaveRequestDto);
    }
    //Get old and new password with dto and change password
    @PatchMapping("/change")
    public ResponseEntity changePassword(@RequestBody UserChangePasswordDto userChangePasswordDto){
        return userService.changePassword(userChangePasswordDto);
    }
    //Delete logged user
    @DeleteMapping("/delete")
    public ResponseEntity deleteUser(){
        return userService.deleteUser(carService);
    }

    //Get the cars of the logged user
    @GetMapping("/getCars")
    public ResponseEntity getCars(){
        return userService.getCars();
    }

    @PostMapping("/logout")
    public ResponseEntity logout(){
        return  userService.logout();
    }

}
