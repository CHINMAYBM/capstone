package com.example.team_service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.team_service.model.Team;

public interface TeamRepository extends MongoRepository<Team, Integer> {
    Optional<Team> findByName(String name);
    List<Team> findBySportCategory(String sportCategory);
    List<Team> findByCoachId(int coachId);
    Optional<Team> findByTeamId(int teamId);
    
}
