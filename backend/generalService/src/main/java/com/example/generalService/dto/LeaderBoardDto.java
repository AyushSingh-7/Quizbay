package com.example.generalService.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class LeaderBoardDto {
    private String id;
    private String name;
    private String type; //(static/dynamic)
    private String contestId;
    private String playerId;
    private Double timeTaken;
    private LocalDate date;
    private Integer score;
    private Integer countOfRightAnswers;
}