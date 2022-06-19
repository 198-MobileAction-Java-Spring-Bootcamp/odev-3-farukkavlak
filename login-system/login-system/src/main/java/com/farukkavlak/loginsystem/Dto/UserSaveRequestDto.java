package com.farukkavlak.loginsystem.Dto;

import lombok.Data;

@Data
public class UserSaveRequestDto {
    private String name;
    private String surname;
    private String username;
    private String email;
    private String password;
}
