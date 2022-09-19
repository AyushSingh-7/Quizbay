package com.example.quiz.controller;

import com.example.quiz.dto.AnswerDto;
import com.example.quiz.entity.Answer;
import com.example.quiz.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@RestController
@RequestMapping(value = "/answer")
@CrossOrigin(value = "*")
public class AnswerController {
    @Autowired
    AnswerService answerService;

    @PostMapping
    public Answer save(Answer answer) {
        return answerService.save(answer);
    }

    @GetMapping(value = "/id/{id}")
    public Optional<AnswerDto> findById(@PathVariable("id") String id) {
        return answerService.findById(id);
    }


    @PutMapping(value = "/update")
    public Answer update(Answer answer) {
        return answerService.update(answer);
    }
}
