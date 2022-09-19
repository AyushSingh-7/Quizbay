package com.example.generalService.service;

import com.example.generalService.dto.SubscriptionDto;
import com.example.generalService.entity.Subscription;

import java.util.List;
import java.util.Optional;

public interface SubscriptionService {

    SubscriptionDto save(SubscriptionDto subscriptionDto);
    List<Subscription> findByPlayerId(String playerId);
    Subscription findBySubscription(String playerId, String contestId);
    Integer countOfSubscription(String contestId);
}
