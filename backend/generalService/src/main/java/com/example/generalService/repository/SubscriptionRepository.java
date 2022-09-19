package com.example.generalService.repository;

import com.example.generalService.entity.Subscription;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SubscriptionRepository extends MongoRepository<Subscription, String> {

    List<Subscription> findByPlayerId(String id);

    //@Query("{$and:[{playerId:?0}, {contestId:?1}]}")
    Subscription findByPlayerIdAndContestId(String playerId, String contestId);

    @Query(value = "{contestId:?0}",count = true)
    Integer countOfSubscription(String contestId);
}
