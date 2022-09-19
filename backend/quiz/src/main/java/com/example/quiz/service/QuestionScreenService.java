package com.example.quiz.service;

import com.example.quiz.dto.QuestionScreenDto;
import com.example.quiz.entity.AnswerScreen;
import com.example.quiz.entity.QuestionScreen;

import java.util.List;
import java.util.Optional;

public interface QuestionScreenService {

    QuestionScreenDto save(QuestionScreenDto questionScreenDto);

    void deleteById(String id);

    QuestionScreen update(QuestionScreen questionScreen);

    Iterable<QuestionScreen>findAll();

    Optional<QuestionScreen> findById(String Id);

    Long countAllDocuments();

    String scrape(String api);

    List<QuestionScreenDto> search(String question);

    QuestionScreenDto urlData (QuestionScreenDto questionScreenDto);
}
