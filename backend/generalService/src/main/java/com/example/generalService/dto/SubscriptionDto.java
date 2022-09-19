package com.example.generalService.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalTime;


@Getter
@Setter
@ToString
public class SubscriptionDto {
    private String playerId;
    private String contestId;
    private LocalTime startTime;
    private LocalTime endTime;
}