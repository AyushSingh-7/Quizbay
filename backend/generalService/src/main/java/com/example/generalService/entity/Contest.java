package com.example.generalService.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@ToString
@Document(collection = "contest")
public class Contest {
    @Id
    private String id;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime startTime;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime endTime;
    // todo : this has to be changed to int / double .. not store localtime
    private Double duration; //total time required to finish quiz
    private String contestName;
    private Integer numberOfQuestions;
    private Integer maximumScore;
    private Integer skipAllowed;
    private String difficultyLevel;
    private Date dateAdded;
    //todo : move the time for answering a question to question level.. not a global value at the contest
    // todo : time should be int / double .. not LocalTime
    //private Double time; //for each question
    private String category;
    private String type; //(static / dynamic)
    private Integer quizMasterId;
    private List<String> questionCode;
    private Boolean isEnabled;
}
