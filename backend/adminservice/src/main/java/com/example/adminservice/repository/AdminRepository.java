package com.example.adminservice.repository;

import com.example.adminservice.entity.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends MongoRepository<Admin, String> {

    Optional<Admin> findByEmailId(String emailId);
    Optional<Admin> findByEmailIdAndPassword(String emailId, String password);
}
