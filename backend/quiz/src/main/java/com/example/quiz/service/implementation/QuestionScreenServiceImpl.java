package com.example.quiz.service.implementation;

import com.example.quiz.dto.QuestionScreenDto;
import com.example.quiz.entity.*;
import com.example.quiz.repository.AnswerScreenRepository;
import com.example.quiz.repository.QuestionScreenRepository;
import com.example.quiz.service.QuestionScreenService;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class QuestionScreenServiceImpl implements QuestionScreenService {
    @Autowired
    QuestionScreenRepository questionScreenRepository;

    @Autowired
    AnswerScreenRepository answerScreenRepository;

    @Override
    public QuestionScreenDto save(QuestionScreenDto questionScreenDto) {
        QuestionScreen questionScreen = new QuestionScreen();
        BeanUtils.copyProperties(questionScreenDto, questionScreen);
        QuestionScreen questionScreen1 = questionScreenRepository.save(questionScreen);
        QuestionScreenDto questionScreenDto1 = new QuestionScreenDto();
        BeanUtils.copyProperties(questionScreen1, questionScreenDto1);
        return questionScreenDto1;
    }

    @Override
    public void deleteById(String id) {
        questionScreenRepository.deleteById(id);
    }

    @Override
    public QuestionScreen update(QuestionScreen questionScreen) {
        Optional<QuestionScreen> optionalQuestion = questionScreenRepository.findById(questionScreen.getId());
        QuestionScreen questionScreen1 = new QuestionScreen();
        if(optionalQuestion.isPresent()) {
            optionalQuestion.get().setQuestion(questionScreen.getQuestion());
            optionalQuestion.get().setAnswerList(questionScreen.getAnswerList());
            BeanUtils.copyProperties(optionalQuestion.get(), questionScreen1);
            questionScreenRepository.save(questionScreen1);
        }
        return questionScreen1;
    }

    @Override
    public Iterable<QuestionScreen> findAll() {
        return questionScreenRepository.findAll();
    }

    @Override
    public Optional<QuestionScreen> findById(String Id) {
        return questionScreenRepository.findById(Id);
    }

    @Override
    public Long countAllDocuments() {
        return questionScreenRepository.countAllDocuments();
    }

    @Override
    public String scrape(String api) {
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = api;
        ResponseEntity<Scrapper> responseEntity = restTemplate.getForEntity(resourceUrl, Scrapper.class);
        ArrayList<Result> resultArrayList = responseEntity.getBody().results;
        for(int i=0; i<resultArrayList.size();i++) {
            if(resultArrayList.get(i).getQuestion().contains("&")) {
                continue;
            }
            QuestionScreen questionScreen = new QuestionScreen();
            questionScreen.setCategory(resultArrayList.get(i).category);
            questionScreen.setQuestionType(resultArrayList.get(i).type);
            questionScreen.setDifficulty(resultArrayList.get(i).difficulty);
            questionScreen.setQuestion(resultArrayList.get(i).question);
            questionScreen.setQuestionFormat("text");
            if(resultArrayList.get(i).difficulty.equalsIgnoreCase("easy")) {
                questionScreen.setScore(1);
            } else if(resultArrayList.get(i).difficulty.equalsIgnoreCase("medium")) {
                questionScreen.setScore(2);
            } else {
                questionScreen.setScore(3);
            }
            List<AnswerScreen> answerScreenList = new ArrayList<>();
            AnswerScreen answerScreen0 = new AnswerScreen();
            answerScreen0.setIsCorrect(true);
            List<Integer> pos = new ArrayList<>();
            pos.add(1);
            pos.add(2);
            pos.add(3);
            int position = i%3;
            position=position+1;
            answerScreen0.setPosition(position);
            pos.remove(pos.indexOf(position));
            answerScreen0.setValue(resultArrayList.get(i).correct_answer);
            answerScreenList.add(answerScreen0);
            answerScreenRepository.save(answerScreen0);
            Collections.shuffle(pos);
            for(int j=0; j<2; j++) {
                AnswerScreen answerScreen = new AnswerScreen();
                answerScreen.setValue(resultArrayList.get(i).incorrect_answers.get(j));
                answerScreen.setPosition(pos.get(j));
                answerScreen.setIsCorrect(false);
                answerScreenList.add(answerScreen);
                answerScreenRepository.save(answerScreen);
            }
            questionScreen.setAnswerList(answerScreenList);
            questionScreenRepository.save(questionScreen);
        }
        return "Successful";
    }

    @Override
    public List<QuestionScreenDto> search(String question) {
        return questionScreenRepository.search(question);
    }

    @Override
    public QuestionScreenDto urlData(QuestionScreenDto questionScreenDto) {
        if(questionScreenDto.getDifficulty().equalsIgnoreCase("easy")) {
            questionScreenDto.setScore(1);
            questionScreenDto.setTime(1.00);
        } else if(questionScreenDto.getDifficulty().equalsIgnoreCase("medium")) {
            questionScreenDto.setScore(2);
            questionScreenDto.setTime(1.30);
        } else {
            questionScreenDto.setTime(2.00);
            questionScreenDto.setScore(3);
        }
        List<AnswerScreen> answerScreenList = new ArrayList<>();
        for(int i=0;i<questionScreenDto.getAnswerList().size();i++){
            answerScreenList.add(questionScreenDto.getAnswerList().get(i));
        }
        QuestionScreen questionScreen = new QuestionScreen();
        BeanUtils.copyProperties(questionScreenDto,questionScreen);
        questionScreen.setAnswerList(answerScreenList);
        questionScreen = questionScreenRepository.save(questionScreen);
        BeanUtils.copyProperties(questionScreen,questionScreenDto);
        return questionScreenDto;
    }
}