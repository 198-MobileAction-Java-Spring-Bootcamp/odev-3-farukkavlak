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

import java.util.regex.Matcher;
import java.util.regex.Pattern;


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
            return new ResponseEntity("Username is already taken!", HttpStatus.BAD_REQUEST);
        }
        // heck for email exists in DB
        if(userDao.existsByEmail(saveRequestDto.getEmail())){
            return new ResponseEntity("Email is already taken!", HttpStatus.BAD_REQUEST);
        }
        String password = saveRequestDto.getPassword();
        if(!isPasswordStrengthEnough(password)){
            return new ResponseEntity("Your password is not strong enough. Your password must contain at least one uppercase letter, one lowercase letter, one number and one symbol.",HttpStatus.BAD_REQUEST);
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
        else if(!isPasswordStrengthEnough(userChangePasswordDto.getNewPassword())){
            return new ResponseEntity("Your password is not strong enough. Your password must contain at least one uppercase letter, one lowercase letter, one number and one symbol.",HttpStatus.BAD_REQUEST);
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
    public ResponseEntity logout() {
        SecurityContextHolder.getContext().setAuthentication(null);
        return new ResponseEntity("Logout is successful",HttpStatus.OK);
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


    //Use regex to determine if password contains symbols
    private boolean isContainSymbol(String password) {
        Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
        Matcher hasSpecial = special.matcher(password);
        return hasSpecial.find();
    }
    //Use regex to determine if password contains number
    private boolean isContainNumber(String password) {
        Pattern digit = Pattern.compile("[0-9]");
        Matcher hasDigit = digit.matcher(password);
        return hasDigit.find();
    }
    //Use regex to determine if password contains upper case
    private boolean isContainUpperCase(String password) {
        for(int letterNo=0;letterNo<password.length();letterNo++){
            if(password.charAt(letterNo)>=65&&password.charAt(letterNo)<=90){
                return true;
            }
        }
        return false;
    }
    //Use regex to determine if password contains lower case
    private boolean isContainLowerCase(String password) {
        for(int letterNo=0;letterNo<password.length();letterNo++){
            if(password.charAt(letterNo)>=97&&password.charAt(letterNo)<=122){
                return true;
            }
        }
        return false;
    }

    private boolean isPasswordStrengthEnough(String password) {
        return isContainUpperCase(password)
                && isContainLowerCase(password)
                && isContainNumber(password)
                && isContainSymbol(password);
    }
}
