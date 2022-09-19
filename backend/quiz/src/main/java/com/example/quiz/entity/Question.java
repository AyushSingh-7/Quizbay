package com.example.quiz.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@ToString
@Document(collection = "question")
public class Question {

    @Id
    private String id;
    private String question;
    private List<Answer> answerList;
    private String difficulty;
    private String category;
    private Integer score;
    private String questionType;
    private Double time;
}
