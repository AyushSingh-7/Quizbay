package com.example.generalService.service.implementation;

import com.example.generalService.dto.LeaderBoardDto;
import com.example.generalService.entity.Contest;
import com.example.generalService.entity.LeaderBoard;
import com.example.generalService.entity.Player;
import com.example.generalService.repository.ContestRepository;
import com.example.generalService.repository.LeaderBoardRepository;
import com.example.generalService.repository.PlayerRepository;
import com.example.generalService.service.LeaderBoardService;
import com.jayway.jsonpath.Criteria;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class LeaderBoardServiceImpl implements LeaderBoardService {
    @Autowired
    LeaderBoardRepository leaderBoardRepository;

    @Autowired
    ContestRepository contestRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Override
    public LeaderBoardDto save(LeaderBoardDto leaderBoardDto) {
        LeaderBoard leaderBoard = new LeaderBoard();
        BeanUtils.copyProperties(leaderBoardDto, leaderBoard);

        Optional<Contest> optionalContest = contestRepository.findById(leaderBoardDto.getContestId());
        leaderBoard.setContest(optionalContest.get());

        Optional<Player> optionalPlayer = playerRepository.findById(leaderBoardDto.getPlayerId());
        optionalPlayer.get().setTotalScore(leaderBoardDto.getScore()+optionalPlayer.get().getTotalScore());
        Double rate = Double.valueOf(optionalPlayer.get().getTotalScore());
        optionalPlayer.get().setUserLevelRating(rate/10);
        playerRepository.save(optionalPlayer.get());
        leaderBoard.setPlayer(optionalPlayer.get());
        LeaderBoard leaderBoard1 = leaderBoardRepository.save(leaderBoard);

        LeaderBoardDto leaderBoardDto1 = new LeaderBoardDto();
        BeanUtils.copyProperties(leaderBoard1, leaderBoardDto1);

        leaderBoardDto1.setType(leaderBoard1.getContest().getType());
        leaderBoardDto1.setContestId(leaderBoard1.getContest().getId());
        leaderBoardDto1.setPlayerId(leaderBoard1.getPlayer().getId());
        leaderBoardDto1.setName(leaderBoard1.getPlayer().getName());
        return leaderBoardDto1;
    }

    @Override
    public List<LeaderBoardDto> findByType(String type) {
        List<LeaderBoard> leaderBoardList = leaderBoardRepository.findByType(type);
        Collections.reverse(leaderBoardList);
        if(leaderBoardList.isEmpty()){
            return null;
        }

        List<LeaderBoardDto> leaderBoardDtoList = new ArrayList<>();

        for (int i=0;i<leaderBoardList.size();i++) {
            LeaderBoardDto leaderBoardDto = new LeaderBoardDto();
            BeanUtils.copyProperties(leaderBoardList.get(i), leaderBoardDto);
            leaderBoardDto.setContestId(leaderBoardList.get(i).getContest().getId());
            leaderBoardDto.setPlayerId(leaderBoardList.get(i).getPlayer().getId());
            leaderBoardDto.setName(leaderBoardList.get(i).getPlayer().getName());
            leaderBoardDto.setType(leaderBoardList.get(i).getContest().getType());
            leaderBoardDtoList.add(leaderBoardDto);
        }
        return leaderBoardDtoList;
    }

    @Override
    public Integer noOfGamesPlayed(String id) {
        return leaderBoardRepository.noOfGamesPlayed(id);
    }

    @Override
    public Integer noOfRightAnswers(String playerId) {
        List<LeaderBoard> leaderBoardList = leaderBoardRepository.findByPlayerId(playerId);
        Integer noOfRightAnswers=0;
        for(int i=0;i<leaderBoardList.size();i++){
            noOfRightAnswers += leaderBoardList.get(i).getCountOfRightAnswers();
        }
        return noOfRightAnswers;
    }

    @Override
    public List<LeaderBoardDto> findByContestId(String contestId) {
        List<LeaderBoard> leaderBoardList = leaderBoardRepository.findByContestId(contestId);
        if(leaderBoardList.isEmpty()){
            return null;
        }

        List<LeaderBoardDto> leaderBoardDtoList = new ArrayList<>();

        for (int i=0;i<leaderBoardList.size();i++) {
            LeaderBoardDto leaderBoardDto = new LeaderBoardDto();
            BeanUtils.copyProperties(leaderBoardList.get(i), leaderBoardDto);
            leaderBoardDto.setContestId(leaderBoardList.get(i).getContest().getId());
            leaderBoardDto.setPlayerId(leaderBoardList.get(i).getPlayer().getId());
            leaderBoardDto.setName(leaderBoardList.get(i).getPlayer().getName());
            leaderBoardDtoList.add(leaderBoardDto);
        }
        return leaderBoardDtoList;
    }

}