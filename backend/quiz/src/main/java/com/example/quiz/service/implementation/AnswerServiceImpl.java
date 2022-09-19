package com.example.quiz.service.implementation;

import com.example.quiz.dto.AnswerDto;
import com.example.quiz.entity.Answer;
import com.example.quiz.repository.AnswerRepository;
import com.example.quiz.service.AnswerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    AnswerRepository answerRepository;

    @Override
    public Answer save(Answer answer) {
        return answerRepository.save(answer);
    }

    @Override
    public Optional<AnswerDto> findById(String id) {
        Optional <Answer> optionalAnswer = answerRepository.findById(id);
        AnswerDto answerDto = null;
        if(optionalAnswer.isPresent()) {
            answerDto = new AnswerDto();
            BeanUtils.copyProperties(optionalAnswer.get(), answerDto);
        }
        return Optional.ofNullable(answerDto);
    }


    @Override
    public Answer update(Answer answer) {
        //write code............
        return null;
    }
}
