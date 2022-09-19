package com.example.quiz.repository;

import com.example.quiz.entity.PlayerEvaluation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerEvaluationRepository extends MongoRepository<PlayerEvaluation, String> {
}
