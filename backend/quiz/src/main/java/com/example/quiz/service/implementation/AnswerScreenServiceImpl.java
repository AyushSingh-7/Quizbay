package com.example.quiz.service.implementation;

import com.example.quiz.controller.AnswerScreenController;
import com.example.quiz.dto.AnswerScreenDto;
import com.example.quiz.entity.AnswerScreen;
import com.example.quiz.repository.AnswerScreenRepository;
import com.example.quiz.service.AnswerScreenService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AnswerScreenServiceImpl implements AnswerScreenService {

    @Autowired
    AnswerScreenRepository answerScreenRepository;

    @Override
    public AnswerScreen save(AnswerScreen answerScreen) {

        return answerScreenRepository.save(answerScreen);
    }

    @Override
    public Optional<AnswerScreenDto> findById(String id) {
        Optional <AnswerScreen> optionalAnswerScreen = answerScreenRepository.findById(id);
        AnswerScreenDto answerScreenDto = null;
        if(optionalAnswerScreen.isPresent()) {
            answerScreenDto = new AnswerScreenDto();
            BeanUtils.copyProperties(optionalAnswerScreen.get(), answerScreenDto);
        }
        return Optional.ofNullable(answerScreenDto);
    }

    @Override
    public AnswerScreen update(AnswerScreen answerScreen) {
        //write query here.......
        return null;
    }
}
