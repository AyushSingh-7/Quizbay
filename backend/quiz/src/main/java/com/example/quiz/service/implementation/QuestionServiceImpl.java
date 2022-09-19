package com.example.quiz.service.implementation;

import com.example.quiz.dto.LeaderBoardDto;
import com.example.quiz.dto.QuestionDto;
import com.example.quiz.entity.Answer;
import com.example.quiz.entity.PlayerEvaluation;
import com.example.quiz.entity.Question;
import com.example.quiz.entity.QuestionScreen;
import com.example.quiz.repository.QuestionRepository;
import com.example.quiz.repository.QuestionScreenRepository;
import com.example.quiz.service.QuestionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    QuestionScreenRepository questionScreenRepository;

    @Override
    public QuestionDto save(QuestionDto questionDto) {
        Question question = new Question();
        BeanUtils.copyProperties(questionDto, question);
        Question question1 = questionRepository.save(question);
        QuestionDto questionDto1 = new QuestionDto();
        BeanUtils.copyProperties(question1, questionDto1);
        return questionDto1;
    }

    @Override
    public void deleteById(String id) {
        questionRepository.deleteById(id);
    }

    @Override
    public QuestionDto update(QuestionDto questionDto) {
        return null;
    }

    @Override
    public Iterable<Question> findAll() {
        return questionRepository.findAll();
    }

    @Override
    public Optional<Question> findById(String id) {
        return questionRepository.findById(id);
    }

    @Override
    public List<String> storeList(List<String> questionId) {
        List<String> questionList = new ArrayList<>();
        for(int i=0;i<questionId.size();i++) {
            Question question = new Question();
            Optional<QuestionScreen> optionalQuestionScreen = questionScreenRepository.findById(questionId.get(i));
            List<Answer> answerList = new ArrayList<>();
            for(int j=0; j<optionalQuestionScreen.get().getAnswerList().size();j++){
                Answer answer = new Answer();
                BeanUtils.copyProperties(optionalQuestionScreen.get().getAnswerList().get(j), answer);
                answerList.add(answer);
            }
            BeanUtils.copyProperties(optionalQuestionScreen.get(), question);
            question.setAnswerList(answerList);
            questionRepository.save(question);
            questionList.add(question.getId());
        }
        return questionList;
    }
}
