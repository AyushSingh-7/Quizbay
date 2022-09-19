package com.example.generalService.service;

import com.example.generalService.dto.LeaderBoardDto;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface LeaderBoardService {
    LeaderBoardDto save (LeaderBoardDto leaderBoardDto);

    List<LeaderBoardDto> findByType(String type);

    Integer noOfGamesPlayed(String id);

    Integer noOfRightAnswers(String playerId);

    List<LeaderBoardDto> findByContestId(String contestId);

}
