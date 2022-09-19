package com.example.generalService.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SignUpResponseDto {
    private String token;
    private String userId;
    private String name;
    private String email;
    private String gender;
    private String password;
    private String mobile;
    private int socialMediaId = 4;
}
