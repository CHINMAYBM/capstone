package com.ustcapstone.goalservice.controller;


import com.ustcapstone.goalservice.exception.GoalCreationException;
import com.ustcapstone.goalservice.exception.GoalNotFoundException;
import com.ustcapstone.goalservice.model.PlayerGoal;
import com.ustcapstone.goalservice.service.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/goals")
//@CrossOrigin(origins = "http://localhost:4200")
public class GoalController {

    @Autowired
    private GoalService goalService;
    
    @PostMapping("/creategoal/")
    public ResponseEntity<PlayerGoal> createGoal(@RequestBody PlayerGoal newGoal) throws GoalCreationException {
        PlayerGoal createdGoal = goalService.createGoal(newGoal);
        return ResponseEntity.ok(createdGoal);
    }

    // Update an existing goal
    @PutMapping("/{goalId}")
    public ResponseEntity<PlayerGoal> updateGoal(@PathVariable int goalId, @RequestBody PlayerGoal updatedGoal) throws GoalNotFoundException {
        try {
            PlayerGoal updatedPlayerGoal = goalService.updateGoal(goalId, updatedGoal);
            return ResponseEntity.ok(updatedPlayerGoal);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();  // Return 404 if goal is not found
        }
    }

    // Get goal by ID
    @GetMapping("/{goalId}")
    public ResponseEntity<PlayerGoal> getGoalById(@PathVariable int goalId) {
        Optional<PlayerGoal> goal = goalService.getGoalById(goalId);
        return goal.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }



    

    // Get goals by Player ID
    @GetMapping("/player/{playerId}")
    public ResponseEntity<List<PlayerGoal>> getGoalsByPlayerId(@PathVariable int playerId) {
        return ResponseEntity.ok(goalService.getGoalsByPlayerId(playerId));
    }

    // Get goals by Coach ID
    @GetMapping("/coach/{coachId}")
    public ResponseEntity<List<PlayerGoal>> getGoalsByCoachId(@PathVariable int coachId) {
        return ResponseEntity.ok(goalService.getGoalsByCoachId(coachId));
    }

    
    @DeleteMapping("/{goalId}")
    public void deleteGoal(@PathVariable int goalId) {
        goalService.deleteGoal(goalId);
    }
}
