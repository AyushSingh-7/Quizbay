package com.example.quiz.controller;

import com.example.quiz.entity.PlayerEvaluation;
import com.example.quiz.service.PlayerEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/evaluate")
public class PlayerEvaluationController {
    @Autowired
    PlayerEvaluationService playerEvaluationService;

    @PostMapping
    PlayerEvaluation evaluate(@RequestBody PlayerEvaluation playerEvaluation) {
        return playerEvaluationService.evaluate(playerEvaluation);
    }
}
