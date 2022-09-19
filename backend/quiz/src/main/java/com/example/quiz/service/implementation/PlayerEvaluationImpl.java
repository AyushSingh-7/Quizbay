package com.example.quiz.service.implementation;

import com.example.quiz.dto.LeaderBoardDto;
import com.example.quiz.entity.Answer;
import com.example.quiz.entity.PlayerEvaluation;
import com.example.quiz.entity.Question;
import com.example.quiz.repository.PlayerEvaluationRepository;
import com.example.quiz.repository.QuestionRepository;
import com.example.quiz.service.PlayerEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class PlayerEvaluationImpl implements PlayerEvaluationService {
    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    PlayerEvaluationRepository playerEvaluationRepository;

    @Override
    public PlayerEvaluation evaluate(PlayerEvaluation playerEvaluation) {
        System.out.println(playerEvaluation);
        int totalScore = 0;
        int countOfRightAnswers=0;
        LinkedHashMap<String, String> results = playerEvaluation.getResults();
        for(Map.Entry<String, String> entry: results.entrySet()) {
            String questionId = entry.getKey();
            String answerValue = entry.getValue();
            Question question = questionRepository.findById(questionId).get();
            List<Answer> answerList = question.getAnswerList();
            for(int i=0;i<answerList.size();i++){
                if(answerList.get(i).getValue().equals(answerValue)){
                     if(answerList.get(i).getIsCorrect()==true) {
                        totalScore=totalScore+question.getScore();
                        countOfRightAnswers++;
                    }
                }
            }
        }
        LeaderBoardDto leaderBoardDto = new LeaderBoardDto();
        leaderBoardDto.setContestId(playerEvaluation.getContestId());
        leaderBoardDto.setPlayerId(playerEvaluation.getPlayerId());
        leaderBoardDto.setDate(LocalDate.now());
        leaderBoardDto.setScore(totalScore);
        leaderBoardDto.setCountOfRightAnswers(countOfRightAnswers);
        leaderBoardDto.setTimeTaken(playerEvaluation.getTimeTaken());
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<LeaderBoardDto> requestEntity = new HttpEntity<>(leaderBoardDto, headers);
        String leaderUrl = "http://localhost:8180/leaderboard";
        ResponseEntity<LeaderBoardDto> response = restTemplate.postForEntity(leaderUrl, requestEntity, LeaderBoardDto.class);
        playerEvaluationRepository.save(playerEvaluation);
        return playerEvaluation;
    }
}
