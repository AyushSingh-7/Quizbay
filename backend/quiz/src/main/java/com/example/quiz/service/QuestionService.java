package com.example.quiz.service;

import com.example.quiz.dto.QuestionDto;
import com.example.quiz.entity.PlayerEvaluation;
import com.example.quiz.entity.Question;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;


public interface QuestionService {

    QuestionDto save(QuestionDto questionDto);

    void deleteById(String id);

    QuestionDto update(QuestionDto questionDto);

    Iterable<Question> findAll();

    Optional<Question> findById(String id);

    List<String> storeList(List<String> questionId);
}
