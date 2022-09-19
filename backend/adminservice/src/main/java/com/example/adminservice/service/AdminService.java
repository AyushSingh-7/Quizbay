package com.example.adminservice.service;

import com.example.adminservice.dto.AdminDto;

import java.util.Optional;

public interface AdminService {

    Boolean save(AdminDto adminDto);

    Optional<AdminDto> findByEmailId(String emailId);

    String login(String emailId, String password);

}
