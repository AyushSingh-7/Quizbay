package com.example.quiz.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;

@Getter
@Setter
@ToString
@Document(collection = "scrapper")
public class Scrapper {
    public Integer response_code;
    public ArrayList<Result> results;
}
