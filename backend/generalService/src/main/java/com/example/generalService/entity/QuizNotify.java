package com.example.generalService.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class QuizNotify {
    private String quizId;
    private String quizType;
    private String quizName;
    private List<String> userId;
    private String startTime;
    private String endTime;
}
