package com.farukkavlak.loginsystem.Dto;

import lombok.Data;

@Data
public class UserChangePasswordDto {
    private String oldPassword;
    private String newPassword;
    private String newPasswordCheck;
}
