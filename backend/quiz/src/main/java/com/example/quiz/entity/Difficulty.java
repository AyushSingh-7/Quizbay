package com.example.quiz.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.IOException;

public enum Difficulty {
    EASY, HARD, MEDIUM;

    @JsonValue
    public String toValue() {
        switch (this) {
            case EASY: return "easy";
            case HARD: return "hard";
            case MEDIUM: return "medium";
        }
        return null;
    }

    @JsonCreator
    public static Difficulty forValue(String value) throws IOException {
        if (value.equals("easy")) return EASY;
        if (value.equals("hard")) return HARD;
        if (value.equals("medium")) return MEDIUM;
        throw new IOException("Cannot deserialize Difficulty");
    }
}
