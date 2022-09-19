package com.example.generalService.controller;

import com.example.generalService.dto.SubscriptionDto;
import com.example.generalService.entity.Subscription;
import com.example.generalService.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/subscription")
@CrossOrigin(value = "*")
public class SubscriptionController {

    @Autowired
    SubscriptionService subscriptionService;

    @PostMapping
    public SubscriptionDto save(@RequestBody SubscriptionDto subscriptionDto) {
        return subscriptionService.save(subscriptionDto);
    }

    @GetMapping(value = "/all/{id}")
    public List<Subscription> findByPlayerId(@PathVariable("id") String playerId) {
        return subscriptionService.findByPlayerId(playerId);
    }

    @GetMapping(value = "/{playerId}/{contestId}")
    public Subscription findBySubscription(@PathVariable("playerId")String playerId, @PathVariable("contestId") String contestId){
        return subscriptionService.findBySubscription(playerId, contestId);
    }

    @GetMapping(value = "/count/{contestId}")
    public Integer countOfSubcription(@PathVariable("contestId") String contestId) {
        return subscriptionService.countOfSubscription(contestId);
    }
}
