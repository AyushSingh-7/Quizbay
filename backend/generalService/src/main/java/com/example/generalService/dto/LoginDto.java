package com.example.generalService.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginDto {
    private String email;
    private String password;
    private int socialMediaId = 4;
}
