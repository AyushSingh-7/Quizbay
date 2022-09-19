package com.example.generalService.repository;
import com.example.generalService.dto.LeaderBoardDto;
import com.example.generalService.entity.LeaderBoard;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface LeaderBoardRepository extends MongoRepository<LeaderBoard, String> {

    @Query(value = "{type:?0}", sort = "{score:1, timeTaken:-1}")
    List<LeaderBoard> findByType(String type);


    @Query(value = "{playerId:?0}",count = true)
    Integer noOfGamesPlayed(String id);

    @Query(value = "{playerId:?0}", sort = "{score:1, timeTaken:-1}")
    List<LeaderBoard> findByPlayerId(String playerId);

    @Query(value = "{contestId:?0}", sort = "{score:1, timeTaken:-1}")
    List<LeaderBoard> findByContestId(String contestId);
}
