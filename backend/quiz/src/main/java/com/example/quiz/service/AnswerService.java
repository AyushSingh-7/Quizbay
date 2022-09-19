package com.example.quiz.service;

import com.example.quiz.dto.AnswerDto;
import com.example.quiz.entity.Answer;

import java.util.Optional;

public interface AnswerService {

    Answer save (Answer answer);

    Optional<AnswerDto> findById(String id);

    Answer update(Answer answer);
}