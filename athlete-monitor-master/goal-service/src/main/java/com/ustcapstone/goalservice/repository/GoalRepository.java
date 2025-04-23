package com.ustcapstone.goalservice.repository;


import com.ustcapstone.goalservice.model.PlayerGoal;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;


public interface GoalRepository extends MongoRepository<PlayerGoal, Integer> {
    List<PlayerGoal> findByPlayerId(int playerId);
    List<PlayerGoal> findByCoachId(int coachId);
    void deleteById(int goalId);

}
