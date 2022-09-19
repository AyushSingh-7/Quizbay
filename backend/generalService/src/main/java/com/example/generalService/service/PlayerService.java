package com.example.generalService.service;

import com.example.generalService.dto.PlayerDto;

import java.util.Optional;

public interface PlayerService {

    Optional<PlayerDto> findById(String id);

    void updateTotalScore(String id, Integer score);

    PlayerDto save(PlayerDto playerDto);

    PlayerDto findByEmailId(String emailId);
}
