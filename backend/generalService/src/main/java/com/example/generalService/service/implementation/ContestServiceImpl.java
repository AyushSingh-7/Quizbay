package com.example.generalService.service.implementation;

import com.example.generalService.dto.ContestDto;
import com.example.generalService.entity.Contest;
import com.example.generalService.repository.ContestRepository;
import com.example.generalService.service.ContestService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Service
public class ContestServiceImpl implements ContestService {
    @Autowired
    ContestRepository contestRepository;

    @Override
    public ContestDto save(ContestDto contestDto) {
        Contest contest = new Contest();
        // todo : based on example discussed during the review, don't store the mins in time format, but change to int / double
        contestDto.setDateAdded(new Date());
        contestDto.setIsEnabled(false);


        //todo :: why are you calling async here?? .. you need to make sure all questions are saved along with contest !!
        RestTemplate restTemplate1 = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity requestEntity = new HttpEntity(contestDto.getQuestionCode(), headers);
        String storeUrl = "http://localhost:8181/question/storelist";
        ResponseEntity<List> responseEntity = restTemplate1.postForEntity(storeUrl, requestEntity, List.class);
        contestDto.setQuestionCode(responseEntity.getBody());
        BeanUtils.copyProperties(contestDto, contest);
        Contest contest1 = contestRepository.save(contest);
        ContestDto contestDto1 = new ContestDto();
        BeanUtils.copyProperties(contest1, contestDto1);
        return contestDto1;
    }

    @Override
    public Optional<ContestDto> findById(String id) {
        //todo : user optional features to create DTO object .. not by implementing if and else conditions
        Optional <Contest> optionalContest = contestRepository.findById(id);
        ContestDto contestDto = new ContestDto();
        BeanUtils.copyProperties(optionalContest.get(), contestDto);
        return Optional.ofNullable(contestDto);
    }

    @Override
    public Iterable<Contest> findAll() {
        return contestRepository.findAll();
    }

    public List<ContestDto> findByType(String type) {
        List<Contest> contestList = contestRepository.findByType(type);
        if(contestList.isEmpty()){
            return null;
        }

        List<ContestDto> contestDtoList = new ArrayList<>();

        for (int i=0;i<contestList.size();i++) {
            ContestDto contestDto = new ContestDto();
            BeanUtils.copyProperties(contestList.get(i), contestDto);
            contestDtoList.add(contestDto);
        }
        return contestDtoList;
    }

    @Override
    public Boolean startContest(ContestDto contestDto) {

        // todo : conditional check for start and end date has to be greater than current time should be in one if / else block .. not as to different blocks
        System.out.println(contestDto);
        if(contestDto.getIsEnabled().equals(false)){
            if(contestDto.getStartDate().equals(LocalDate.now()) || contestDto.getEndDate().equals(LocalDate.now()) || (LocalDate.now().isAfter(contestDto.getStartDate()) && LocalDate.now().isBefore(contestDto.getEndDate()))) {
                if(contestDto.getStartTime().equals(LocalTime.now()) || (LocalTime.now().isAfter(contestDto.getStartTime()))){
                    contestDto.setIsEnabled(true);
                    Contest contest = contestRepository.findById(contestDto.getId()).get();
                    contest.setIsEnabled(true);
                    contestRepository.save(contest);
                    return true;
                }
            }
        } else if (contestDto.getIsEnabled()==true) {
            if(contestDto.getEndDate().equals(new Date()) || LocalDate.now().isAfter(contestDto.getEndDate())) {
                if(contestDto.getEndTime().equals(LocalTime.now()) || LocalTime.now().isAfter(contestDto.getEndTime())){
                    contestDto.setIsEnabled(false);
                    Contest contest = contestRepository.findById(contestDto.getId()).get();
                    contest.setIsEnabled(false);
                    contestRepository.save(contest);
                    return false;
                }
            }
            return  true;
        }
        return false;
    }

    @Override
    public void deleteById(String id) {
        contestRepository.deleteById(id);
    }
}
