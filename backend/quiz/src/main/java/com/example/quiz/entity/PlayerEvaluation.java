package com.example.quiz.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.LinkedHashMap;

@Getter
@Setter
@ToString
@Document(collection = "evaluation")

public class PlayerEvaluation {
    private String id;
    private String playerId;
    private String contestId;
    private Double timeTaken;
    private LinkedHashMap<String, String> results;
}
