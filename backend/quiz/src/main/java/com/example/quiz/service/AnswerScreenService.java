package com.example.quiz.service;


import com.example.quiz.dto.AnswerScreenDto;
import com.example.quiz.entity.AnswerScreen;

import java.util.Optional;

public interface AnswerScreenService {

    AnswerScreen save (AnswerScreen answerScreen);

    Optional<AnswerScreenDto> findById(String id);

    AnswerScreen update(AnswerScreen answerScreen);

}
