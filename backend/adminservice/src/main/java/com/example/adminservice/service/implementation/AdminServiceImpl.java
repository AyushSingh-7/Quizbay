package com.example.adminservice.service.implementation;

import com.example.adminservice.dto.AdminDto;
import com.example.adminservice.entity.Admin;
import com.example.adminservice.repository.AdminRepository;
import com.example.adminservice.service.AdminService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminRepository adminRepository;

    @Override
    public Boolean save(AdminDto adminDto) {
        if(!adminDto.getConfirmPassword().equals(adminDto.getPassword())){
            System.out.println("passs");
            return  false;
        }

        Optional<Admin> optionalAdmin = adminRepository.findByEmailId(adminDto.getEmailId());
        if(optionalAdmin.isPresent()) {
            System.out.println("avail");
            return false;
        }

        // todo : add conditions to check if the role is "scrapper", .... etc
        if(!adminDto.getRole().equalsIgnoreCase("scrapper") && !adminDto.getRole().equalsIgnoreCase("screener") && !adminDto.getRole().equalsIgnoreCase("quizmaster")) {
            System.out.println("cases");
            return false;
        }

        Admin admin = new Admin();
        String pass = encrypt(adminDto.getPassword());
        adminDto.setPassword(pass);
        adminDto.setConfirmPassword(pass);
        BeanUtils.copyProperties(adminDto, admin);
        Admin admin1 = adminRepository.save(admin);
        AdminDto adminDto1 = new AdminDto();
        BeanUtils.copyProperties(admin1, adminDto1);
        return true;

    }

    @Override
    public Optional<AdminDto> findByEmailId(String emailId) {

        // todo : use optional methods on Optional<Admin> to come up with output .. rather than developing if else conditions

        Optional<Admin> optionalAdmin = adminRepository.findByEmailId(emailId);
        AdminDto adminDto = new AdminDto();
        BeanUtils.copyProperties(optionalAdmin.get(), adminDto);
        return Optional.ofNullable(adminDto);
    }

    @Override
    public String login(String emailId, String password) {
        Optional<Admin> optionalAdmin = adminRepository.findByEmailIdAndPassword(emailId, encrypt(password));
        return  optionalAdmin.get().getRole();
    }

    public String encrypt(String password){
        String pass="";
        for(int i=0;i<password.length();i++){
            int value = password.charAt(i);
            value=value+i+1;
            char character = (char) value;
            pass = pass+character;
        }
        return pass;
    }
}
