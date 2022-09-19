package com.example.generalService.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalTime;

@Getter
@Setter
@ToString
@Document(collection = "subscription")
public class Subscription{
    @Id
    private String id;
    private Player player;
    private Contest contest;
    private LocalTime startTime;
    private LocalTime endTime;
    // todo : add the start and end time for the user level !!
}
