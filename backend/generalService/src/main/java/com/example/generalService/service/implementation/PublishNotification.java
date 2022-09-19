package com.example.generalService.service.implementation;


import com.example.generalService.entity.QuizNotify;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PublishNotification {

    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;

    ObjectMapper objectMapper=new ObjectMapper();

    public void pushQuizNotification() throws JsonProcessingException
    {

        QuizNotify quizNotify=new QuizNotify();



        kafkaTemplate.send("quiz",objectMapper.writeValueAsString(quizNotify));

    }
}
