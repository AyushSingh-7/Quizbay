package com.example.generalService.controller;

import com.example.generalService.dto.PlayerDto;
import com.example.generalService.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/player")
@CrossOrigin(value = "*")
public class PlayerController {
    @Autowired
    PlayerService playerService;

    @GetMapping(value = "/{id}")
    Optional<PlayerDto> findById(@PathVariable("id") String id){
        return playerService.findById(id);
    }

    @PutMapping(value = "/{id}/{score}")
    public void updateTotalScore(@PathVariable("id") String id, @PathVariable("score") Integer score){
        playerService.updateTotalScore(id, score);
    }
    @PostMapping
    public PlayerDto save(@RequestBody PlayerDto playerDto){
        return playerService.save(playerDto);
    }

    @GetMapping(value = "/email/{emailId}")
    public PlayerDto findByEmailId(@PathVariable("emailId") String emailId){
        return playerService.findByEmailId(emailId);
    }
}
