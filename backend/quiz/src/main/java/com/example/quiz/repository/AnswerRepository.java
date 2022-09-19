package com.example.quiz.repository;

import com.example.quiz.entity.Answer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnswerRepository extends MongoRepository<Answer, String> {

}
