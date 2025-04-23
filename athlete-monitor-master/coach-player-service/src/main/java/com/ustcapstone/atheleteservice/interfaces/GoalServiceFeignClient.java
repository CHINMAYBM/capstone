package com.ustcapstone.atheleteservice.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ustcapstone.atheleteservice.model.PlayerGoal;

import java.util.List;

@FeignClient(name = "goal-service", url = "http://localhost:8094/api/goals")  // The URL should be the actual URL of the Goal Service
public interface GoalServiceFeignClient {
	
	@PostMapping("/creategoal/")
   PlayerGoal createGoal(@RequestBody PlayerGoal newGoal);

 // Get all goals for a specific player
 @GetMapping("/player/{playerId}")
 List<PlayerGoal> getGoalsByPlayerId(@PathVariable("playerId") int playerId);

 // Get all goals given by a specific coach
 @GetMapping("/coach/{coachId}")
 List<PlayerGoal> getGoalsByCoachId(@PathVariable("coachId") int coachId);

 // Get a specific goal by goalId
 @GetMapping("/{goalId}")
 PlayerGoal getGoalById(@PathVariable("goalId") int goalId);

 // Update a goal by goalId
 @PutMapping("/{goalId}")
 PlayerGoal updateGoal(@PathVariable("goalId") int goalId, @RequestBody PlayerGoal updatedGoal);
 
 @DeleteMapping("/{goalId}")
 void deleteGoal(@PathVariable int goalId);
}
