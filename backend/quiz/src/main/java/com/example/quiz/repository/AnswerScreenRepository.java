package com.example.quiz.repository;

import com.example.quiz.entity.AnswerScreen;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface AnswerScreenRepository extends MongoRepository<AnswerScreen, String> {

}
