package com.ustcapstone.trainingsession.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ustcapstone.trainingsession.model.TrainingSession;

import java.util.List;

public interface TrainingSessionRepository extends MongoRepository<TrainingSession, Integer> {
	List<TrainingSession> findByPlayerIdsContaining(int playerId);
    List<TrainingSession> findByCoachId(int coachId);
}