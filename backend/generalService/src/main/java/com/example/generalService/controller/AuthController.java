package com.example.generalService.controller;

import com.example.generalService.dto.LoginDto;
import com.example.generalService.dto.PlayerDto;
import com.example.generalService.dto.SignUpDto;
import com.example.generalService.dto.SignUpResponseDto;
import com.example.generalService.entity.Player;
import com.example.generalService.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {
    @Autowired
    PlayerService playerService;

    @PostMapping (value = "/signup")
    public PlayerDto signUp(@RequestBody SignUpDto signUpDto){
        System.out.println(signUpDto);
        SignUpResponseDto signUpResponseDto = null;

        PlayerDto playerDto = new PlayerDto();
        playerDto.setEmailId(signUpDto.getEmail());
        playerDto.setName(signUpDto.getName());
        playerDto.setGender(signUpDto.getGender());
        playerService.save(playerDto);

        RestTemplate restTemplate = new RestTemplate();
        try{
            signUpResponseDto = restTemplate.postForObject("http://10.20.3.120:8111/user/signup",signUpDto,SignUpResponseDto.class);
            System.out.println(signUpResponseDto);
        }
        catch (Exception e){
            System.out.println(e);
        }
        return playerDto;
    }
}
