package com.example.generalService.controller;

import com.example.generalService.dto.LeaderBoardDto;
import com.example.generalService.service.LeaderBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/leaderboard")
@CrossOrigin(value = "*")
public class LeaderBoardController {
    @Autowired
    LeaderBoardService leaderBoardService;

    @PostMapping
    public LeaderBoardDto save(@RequestBody LeaderBoardDto leaderBoardDto) {
        return leaderBoardService.save(leaderBoardDto);
    }

    @GetMapping(value = "/{type}")
    public List<LeaderBoardDto> findByType(@PathVariable("type")String type){
        return leaderBoardService.findByType(type);
    }

    @GetMapping(value = "/gamesplayed/{id}")
    public Integer noOfGamesPlayed(@PathVariable("id") String id) {
        return leaderBoardService.noOfGamesPlayed(id);
    }

    @GetMapping(value = "/right/{id}")
    public Integer noOfRightAnswers(@PathVariable("id") String playerId){
        return leaderBoardService.noOfRightAnswers(playerId);
    }

    @GetMapping(value = "/list/{contestId}")
    public List<LeaderBoardDto> findByContestId(@PathVariable("contestId") String contestId){
        return leaderBoardService.findByContestId(contestId);
    }
}
