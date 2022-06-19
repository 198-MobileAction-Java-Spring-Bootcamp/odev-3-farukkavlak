package com.farukkavlak.loginsystem.Service;

import com.farukkavlak.loginsystem.Converter.CarMapper;
import com.farukkavlak.loginsystem.Converter.UserMapper;
import com.farukkavlak.loginsystem.Dao.UserDao;
import com.farukkavlak.loginsystem.Dto.*;
import com.farukkavlak.loginsystem.Entity.Car;
import com.farukkavlak.loginsystem.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private AuthenticationManager authenticationManager;
    public ResponseEntity saveUser(UserSaveRequestDto saveRequestDto){
        UserMapper userMapper = new UserMapper();
        // check for username exists in a DB
        if(userDao.existsByUsername(saveRequestDto.getUsername())){
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }
        // heck for email exists in DB
        if(userDao.existsByEmail(saveRequestDto.getEmail())){
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }
        // create user object
        User user = userMapper.convertToUser(saveRequestDto);
        userDao.save(user);
        return new ResponseEntity<>("User saved", HttpStatus.CREATED);
    }
    public ResponseEntity loginUser(UserLoginDto userLoginDto){
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userLoginDto.getUsername(), userLoginDto.getPassword()
                    )
            );
        } catch (Exception e) {
            return new ResponseEntity<>("Username or Password is wrong", HttpStatus.BAD_REQUEST);
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("Login is successful", HttpStatus.CREATED);
    }

    public ResponseEntity changePassword(UserChangePasswordDto userChangePasswordDto) {
        User user = getLoggedUser();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if(!isCorrect(userChangePasswordDto, user, passwordEncoder)){
            return new ResponseEntity<>("Old password is not correct", HttpStatus.BAD_REQUEST);
        }
        else if(isEqualsNewPasswords(userChangePasswordDto)) {
            user.setPassword(passwordEncoder.encode(userChangePasswordDto.getNewPassword()));
            userDao.save(user);
            return new ResponseEntity<>("Password changed", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("New passwords are not match", HttpStatus.BAD_REQUEST);
        }
    }
    public ResponseEntity deleteUser(CarService carService) {
        User user = getLoggedUser();
        for (Car car : user.getCars()){
            carService.deleteCarWithPlate(car.getPlate(),this);
        }
        userDao.delete(user);
        return new ResponseEntity<>("User Deleted", HttpStatus.OK);
    }


    /*
    Helper functions
    */
     public User getLoggedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if(principal instanceof UserDetails){
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
         return userDao.findByUsername(username).orElseThrow();
    }

    public ResponseEntity getCars() {
        User user = getLoggedUser();
        CarMapper carMapper = new CarMapper();
        return new ResponseEntity(carMapper.convertToCarDtoList(user.getCars()),HttpStatus.OK);
    }

    private boolean isCorrect(UserChangePasswordDto userChangePasswordDto, User user, BCryptPasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(userChangePasswordDto.getOldPassword(), user.getPassword());
    }

    private boolean isEqualsNewPasswords(UserChangePasswordDto userChangePasswordDto) {
        return userChangePasswordDto.getNewPassword().equals(userChangePasswordDto.getNewPasswordCheck());
    }
}
