package com.example.generalService.repository;

import com.example.generalService.dto.PlayerDto;
import com.example.generalService.entity.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends MongoRepository<Player, String> {
    Player findByEmailId(String emailId);
}
