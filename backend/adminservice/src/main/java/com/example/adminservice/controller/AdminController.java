package com.example.adminservice.controller;

import com.example.adminservice.dto.AdminDto;
import com.example.adminservice.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/admin")
@CrossOrigin(value = "*")
public class AdminController {
    @Autowired
    AdminService adminService;


    @PostMapping(value = "/register")
    public Boolean save(@RequestBody AdminDto adminDto) {
        return adminService.save(adminDto);
    }

    @GetMapping(value = "/{emailId}")
    public Optional<AdminDto> findByEmailId(@PathVariable String emailId) {
        return adminService.findByEmailId(emailId);
    }

    @PostMapping(value = "/login/{emailId}/{password}")
    public String login(@PathVariable("emailId")String emailId, @PathVariable("password") String password){
        return adminService.login(emailId, password);
    }
}
