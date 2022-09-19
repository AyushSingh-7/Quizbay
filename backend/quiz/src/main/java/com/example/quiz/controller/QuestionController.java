package com.example.quiz.controller;

import com.example.quiz.dto.QuestionDto;
import com.example.quiz.entity.Question;
import com.example.quiz.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/question")
@CrossOrigin(value = "*")
public class QuestionController {
    @Autowired
    QuestionService questionService;


    @PostMapping
    public QuestionDto save(QuestionDto questionDto) {
        return questionService.save(questionDto);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteById(String id) {
        questionService.deleteById(id);
    }

    @PutMapping(value = "/update")
    public QuestionDto update(@RequestBody QuestionDto questionDto) {
        return questionService.update(questionDto);
    }

    @GetMapping(value = "/list")
    public Iterable<Question> findAll() {
        return questionService.findAll();
    }

    @GetMapping(value = "/id/{id}")
    public Optional<Question> findById(@PathVariable("id") String id) {
        return questionService.findById(id);
    }

    @PostMapping(value = "/storelist")
    public List<String> storeQuestions(@RequestBody List<String> questionId){
        return questionService.storeList(questionId);
    }

}
