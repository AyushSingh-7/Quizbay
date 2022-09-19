package com.example.generalService.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@Document(collection = "player")
public class Player {
    @Id
    private String id;
    private String name;
    private String gender;
    private String emailId;
    private Double userLevelRating;
    private Integer totalScore;
}
