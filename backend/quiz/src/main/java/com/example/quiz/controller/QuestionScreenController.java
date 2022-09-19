package com.example.quiz.controller;

import com.example.quiz.dto.QuestionScreenDto;
import com.example.quiz.entity.QuestionScreen;
import com.example.quiz.service.AnswerScreenService;
import com.example.quiz.service.QuestionScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/questionscreen")
@CrossOrigin(value = "*")
public class QuestionScreenController {
    @Autowired
    QuestionScreenService questionScreenService;

    @Autowired
    AnswerScreenService answerScreenService;

    @PostMapping
    public QuestionScreenDto save(@RequestBody QuestionScreenDto questionScreenDto) {
        return questionScreenService.save(questionScreenDto);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable("id") String id) {
        questionScreenService.deleteById(id);
    }

    @PutMapping(value = "/update")
    public QuestionScreen update(@RequestBody QuestionScreen questionScreen) {
        return questionScreenService.update(questionScreen);
    }

    @GetMapping(value = "/list")
    public Iterable<QuestionScreen> findAll() {
        return questionScreenService.findAll();
    }

    @GetMapping(value = "/id/{id}")
    public Optional<QuestionScreen> findById(@PathVariable("id") String id) {
        return questionScreenService.findById(id);
    }

    @PostMapping(value = "/scrapper")
    public String scrapeApi (@RequestParam String api) {
        return questionScreenService.scrape(api);
    }

    @GetMapping(value = "/search/{question}")
    public List<QuestionScreenDto> search(@PathVariable("question") String question) {
        return questionScreenService.search(question);
    }

    @PostMapping(value = "/url")
    public QuestionScreenDto urlData(@RequestBody QuestionScreenDto questionScreenDto){
        return questionScreenService.urlData(questionScreenDto);
    }

}