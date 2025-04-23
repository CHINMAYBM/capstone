package com.ustcapstone.goalservice.service;

import com.ustcapstone.goalservice.exception.GoalCreationException;
import com.ustcapstone.goalservice.model.PlayerGoal;
import com.ustcapstone.goalservice.repository.GoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class GoalService {
     
	 @Autowired
    private  GoalRepository goalRepository;

	 
	 

	 public PlayerGoal createGoal(PlayerGoal goal) throws GoalCreationException {
	        if (goal == null || goal.getPlayerId() == 0) {
	            throw new GoalCreationException("Invalid goal data.");
	        }
	        long goalCount = goalRepository.count(); 
	        int nextPlayerId = (int) (goalCount + 1);  
	        goal.setGoalId(nextPlayerId);

	        return goalRepository.save(goal);
	    }

	    // Get goal by ID
	    public Optional<PlayerGoal> getGoalById(int goalId) {
	        return goalRepository.findById(goalId);
	    }

	    // Update an existing goal
	    public PlayerGoal updateGoal(int goalId, PlayerGoal updatedGoal) {
	        Optional<PlayerGoal> existingGoalOpt = getGoalById(goalId);

	        if (existingGoalOpt.isEmpty()) {
	            throw new RuntimeException("Goal not found");
	        }

	        PlayerGoal existingGoal = existingGoalOpt.get();

	        // Update the existing goal with the new values (only fields provided in updatedGoal)
	        existingGoal.setAchievedValue(updatedGoal.getAchievedValue());
	        existingGoal.setStatus(updatedGoal.getStatus());
	        existingGoal.setFeedbackRemarks(updatedGoal.getFeedbackRemarks());

	        // Save and return the updated goal
	        return goalRepository.save(existingGoal);
	    }
	    
	    
	    public void deleteGoal(int goalId) {
	        goalRepository.deleteById(goalId);
	    }
	 

    // Fetch goals for a player by player ID
    public List<PlayerGoal> getGoalsByPlayerId(int playerId) {
        return goalRepository.findByPlayerId(playerId);
    }
   

    // Fetch goals created by a coach by coach ID
    public List<PlayerGoal> getGoalsByCoachId(int coachId) {
        return goalRepository.findByCoachId(coachId);
    }


  
    
}
