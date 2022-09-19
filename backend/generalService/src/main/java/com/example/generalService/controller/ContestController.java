package com.example.generalService.controller;

import com.example.generalService.dto.ContestDto;
import com.example.generalService.entity.Contest;
import com.example.generalService.service.ContestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping(value = "/contest")
@CrossOrigin(value = "*")
public class ContestController {

    @Autowired
    ContestService contestService;

    @PostMapping
    public ContestDto save (@RequestBody ContestDto contestDto) {
        return contestService.save(contestDto);
    }


    @GetMapping(value = "/{id}")
    public Optional<ContestDto> findById (@PathVariable("id") String id){
        return contestService.findById(id);
    }


    @GetMapping(value = "/list")
    public Iterable<Contest> findAll(){
        return contestService.findAll();
    }

    @PostMapping(value = "/start")
    public Boolean startContest (@RequestBody ContestDto contestDto){
        return contestService.startContest(contestDto);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteContest(@PathVariable("id") String id){
        contestService.deleteById(id);
    }

}
