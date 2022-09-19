package com.example.quiz.dto;

import com.example.quiz.entity.AnswerScreen;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.List;

@Getter
@Setter
@ToString
public class QuestionScreenDto {
    private String id;
    private String question;
    private String category;
    private String difficulty;
    private Integer score;
    private String questionType;
    private List<AnswerScreen> answerList;
    private String url;
    private String questionFormat;
    private Double time;

}
