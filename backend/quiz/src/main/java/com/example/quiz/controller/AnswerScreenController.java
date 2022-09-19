package com.example.quiz.controller;

import com.example.quiz.dto.AnswerScreenDto;
import com.example.quiz.entity.AnswerScreen;
import com.example.quiz.service.AnswerScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@RestController
@RequestMapping(value = "/answerscreen")
@CrossOrigin(value = "*")
public class AnswerScreenController {
    @Autowired
    AnswerScreenService answerScreenService;

    @PostMapping
    public AnswerScreen save(@RequestBody AnswerScreen answerScreen) {
        return answerScreenService.save(answerScreen);
    }

    @GetMapping(value = "/id/{id}")
    public Optional<AnswerScreenDto> findById(@PathVariable("id") String id) {
        return answerScreenService.findById(id);
    }


    @PutMapping(value = "/update")
    public AnswerScreen update(@RequestBody AnswerScreen answerScreen) {
        return answerScreenService.update(answerScreen);
    }
}
