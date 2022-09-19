package com.example.quiz.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@ToString
@Document(collection = "results")
public class Result {
    public String category;
    public String type;
    public String difficulty;
    public String question;
    public String correct_answer;
    public ArrayList<String> incorrect_answers;
}

