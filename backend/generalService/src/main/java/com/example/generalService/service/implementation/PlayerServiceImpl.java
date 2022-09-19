package com.example.generalService.service.implementation;

import com.example.generalService.controller.PlayerController;
import com.example.generalService.dto.PlayerDto;
import com.example.generalService.entity.Player;
import com.example.generalService.repository.PlayerRepository;
import com.example.generalService.service.PlayerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    PlayerRepository playerRepository;

    @Override
    public Optional<PlayerDto> findById(String id) {
        // todo : optional methods to create response object
        Optional <Player> optionalPlayer = playerRepository.findById(id);
        PlayerDto playerDto = new PlayerDto();
        BeanUtils.copyProperties(optionalPlayer.get(), playerDto);
        return Optional.ofNullable(playerDto);
    }

    @Override
    public void updateTotalScore(String id, Integer score) {
        // todo : optional methods to create response object
        Optional<Player> optionalPlayer = playerRepository.findById(id);
        Player player = new Player();
        // todo : use the inplace update to change the score and ranking .. not load object
        Integer prevScore = optionalPlayer.get().getTotalScore();
        prevScore = prevScore + score;
        optionalPlayer.get().setTotalScore(prevScore);
        double rate = prevScore/10;
        optionalPlayer.get().setUserLevelRating(rate);
        BeanUtils.copyProperties(optionalPlayer.get(), player);
        playerRepository.save(player);
    }

    @Override
    public PlayerDto save(PlayerDto playerDto) {
        Player player = new Player();
        BeanUtils.copyProperties(playerDto, player);
        player.setUserLevelRating(0.00);
        player.setTotalScore(0);
        Player player1 = playerRepository.save(player);
        PlayerDto playerDto1 = new PlayerDto();
        BeanUtils.copyProperties(player1, playerDto1);
        return playerDto1;
    }

    @Override
    public PlayerDto findByEmailId(String emailId) {
        Player player = playerRepository.findByEmailId(emailId);
        PlayerDto playerDto = new PlayerDto();
        BeanUtils.copyProperties(player, playerDto);
        return playerDto;
    }
}