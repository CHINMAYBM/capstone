package com.ustcapstone.trainingsession.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ustcapstone.trainingsession.model.TrainingSession;
import com.ustcapstone.trainingsession.services.TrainingSessionService;

import java.util.List;

@RestController
@RequestMapping("/api/training-sessions")
//@CrossOrigin(origins = "http://localhost:4200")
public class TrainingSessionController {

    @Autowired
    private TrainingSessionService trainingSessionService;

    // Create Training Session
    @PostMapping
    public ResponseEntity<TrainingSession> createTrainingSession(@RequestBody TrainingSession session) {
    	
    	return ResponseEntity.ok(trainingSessionService.createTrainingSession(session));
    }

    // Update Training Session
    @PutMapping
    public ResponseEntity<TrainingSession> updateTrainingSession(@RequestBody TrainingSession session) {
        return ResponseEntity.ok(trainingSessionService.updateTrainingSession(session));
    }

    @GetMapping("/player/{playerId}")
    public ResponseEntity<List<TrainingSession>> getSessionsByPlayerId(@PathVariable int playerId) {
        List<TrainingSession> sessions = trainingSessionService.getTrainingSessionsByPlayerId(playerId);
        return ResponseEntity.ok(sessions);
    }

    @GetMapping("/coach/{coachId}")
    public ResponseEntity<List<TrainingSession>> getSessionsByCoachId(@PathVariable int coachId) {
        List<TrainingSession> sessions = trainingSessionService.getTrainingSessionsByCoachId(coachId);
        return ResponseEntity.ok(sessions);
    }
    
    @DeleteMapping("/{sessionId}")
    public ResponseEntity<Void> deleteTrainingSession(@PathVariable int sessionId) {
        trainingSessionService.deleteTrainingSession(sessionId);
        return ResponseEntity.noContent().build();
    }
  
}
