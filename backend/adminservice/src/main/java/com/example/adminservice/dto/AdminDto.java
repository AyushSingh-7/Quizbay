package com.example.adminservice.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AdminDto {
    private String emailId;
    private String name;
    private String password;
    private String confirmPassword;
    private String role;
}
