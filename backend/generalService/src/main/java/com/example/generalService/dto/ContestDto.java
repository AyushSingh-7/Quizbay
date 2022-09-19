package com.example.generalService.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.net.URL;
import java.util.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@ToString
@CrossOrigin(value = "*")
public class ContestDto {
        private String id;
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        private LocalDate startDate;
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
        private LocalTime startTime;
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        private LocalDate endDate;
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
        private LocalTime endTime;
        private Double duration; //total time required to finish quiz
        private String contestName;
        private Integer numberOfQuestions;
        private Integer maximumScore;
        private Integer skipAllowed;
        private String difficultyLevel;
        private Date dateAdded;
        //private Double time; //for each question
        private String category;
        private String type; //(static / dynamic)
        private String quizMasterId;
        private List<String> questionCode;
        private Boolean isEnabled;
}
