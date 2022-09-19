package com.example.generalService.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.cglib.core.Local;
import org.springframework.core.annotation.Order;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@ToString
@Document(collection = "leaderboard")
public class LeaderBoard {
    @Id
    private String id;
    private String type; //(static/dynamic)
    private Contest contest;
    // todo : keep player and remove name from the entity .. user can change the name anytime in the profile !!
    private Player player;
    private Double timeTaken;
    private LocalDate date;
    private Integer score;
    private Integer countOfRightAnswers;
}
