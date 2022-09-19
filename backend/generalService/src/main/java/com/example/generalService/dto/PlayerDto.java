package com.example.generalService.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PlayerDto {
    private String id;
    private String name;
    private String emailId;
    private String gender;
    private Double userLevelRating;
    private Integer totalScore;
}
