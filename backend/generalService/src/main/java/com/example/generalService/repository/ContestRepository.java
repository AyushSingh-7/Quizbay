package com.example.generalService.repository;

import com.example.generalService.dto.ContestDto;
import com.example.generalService.entity.Contest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContestRepository extends MongoRepository<Contest, String> {
    List<Contest> findByType(String type);
}
