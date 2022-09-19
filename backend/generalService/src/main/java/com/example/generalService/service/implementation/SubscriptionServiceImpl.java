package com.example.generalService.service.implementation;

import com.example.generalService.dto.SubscriptionDto;
import com.example.generalService.entity.Contest;
import com.example.generalService.entity.Player;
import com.example.generalService.entity.Subscription;
import com.example.generalService.repository.ContestRepository;
import com.example.generalService.repository.PlayerRepository;
import com.example.generalService.repository.SubscriptionRepository;
import com.example.generalService.service.SubscriptionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Autowired
    ContestRepository contestRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Override
    public SubscriptionDto save(SubscriptionDto subscriptionDto) {
        System.out.println(subscriptionDto.toString());
        Subscription subscription = new Subscription();
        BeanUtils.copyProperties(subscriptionDto, subscription);

        Optional<Contest> optionalContest = contestRepository.findById(subscriptionDto.getContestId());
        subscription.setContest(optionalContest.get());

        Optional<Player> optionalPlayer = playerRepository.findById(subscriptionDto.getPlayerId());
        subscription.setPlayer(optionalPlayer.get());

        Subscription subscription1 = subscriptionRepository.save(subscription);

        SubscriptionDto subscriptionDto1 = new SubscriptionDto();
        BeanUtils.copyProperties(subscription1, subscriptionDto1);

        subscriptionDto1.setStartTime((subscription1.getContest().getStartTime()));
        subscriptionDto1.setEndTime((subscription1.getContest().getEndTime()));
        subscriptionDto1.setContestId(subscription1.getContest().getId());
        subscriptionDto1.setPlayerId(subscription1.getPlayer().getId());

        return subscriptionDto1;
    }

    @Override
    public List<Subscription> findByPlayerId(String playerId) {
        return subscriptionRepository.findByPlayerId(playerId);
    }

    @Override
    public Subscription findBySubscription(String playerId, String contestId) {
        return subscriptionRepository.findByPlayerIdAndContestId(playerId, contestId);
    }

    @Override
    public Integer countOfSubscription(String contestId) {
        return subscriptionRepository.countOfSubscription(contestId);
    }


}
