package com.example.quiz.dto;

import com.example.quiz.entity.Answer;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class QuestionDto {
    private String id;
    private String question;
    private String category;
    private String difficulty;
    private Integer score;
    private String questionType;
    private List<AnswerDto> answerList;
    private Double time;
}
