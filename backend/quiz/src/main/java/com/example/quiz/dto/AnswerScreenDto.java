package com.example.quiz.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
public class AnswerScreenDto {
    private String id;
    private String value;
    private Integer position;
}
