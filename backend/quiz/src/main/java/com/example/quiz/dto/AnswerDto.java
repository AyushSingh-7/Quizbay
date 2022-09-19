package com.example.quiz.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AnswerDto {
    private String id;
    private String value;
    private Integer position;
}