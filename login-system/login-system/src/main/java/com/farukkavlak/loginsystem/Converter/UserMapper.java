package com.farukkavlak.loginsystem.Converter;

import com.farukkavlak.loginsystem.Dto.UserSaveRequestDto;
import com.farukkavlak.loginsystem.Entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserMapper {

    //UserSaveRequestDto -> User
    public User convertToUser(UserSaveRequestDto userSaveRequestDto){
        User user = new User();
        user.setName(userSaveRequestDto.getName());
        user.setSurname(userSaveRequestDto.getSurname());
        user.setUsername(userSaveRequestDto.getUsername());
        user.setEmail(userSaveRequestDto.getEmail());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(userSaveRequestDto.getPassword());
        user.setPassword(encodedPassword);
        return user;
    }
}
