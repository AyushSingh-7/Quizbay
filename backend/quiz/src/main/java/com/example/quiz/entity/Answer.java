package com.example.quiz.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@Document(collection = "answer")
public class Answer {
    private String id;
    private String value;
    private Integer position;
    private Boolean isCorrect;
}
