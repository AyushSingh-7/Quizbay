package com.example.quiz.repository;

import com.example.quiz.dto.QuestionScreenDto;
import com.example.quiz.entity.QuestionScreen;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface QuestionScreenRepository extends MongoRepository<QuestionScreen, String> {

    @Query(value = "{}", count = true)
    public Long countAllDocuments();

    @Query("{question:{ $regex: ?0, $options: 'i'}}")
    List<QuestionScreenDto> search(String question);
}
