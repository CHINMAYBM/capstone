package com.ustcapstone.atheleteservice.interfaces;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ustcapstone.atheleteservice.model.TrainingSession;

import java.util.List;

@FeignClient(name = "trainingsession-service", url = "http://localhost:8092/api/training-sessions")
public interface TrainingSessionFeignClient {

	@GetMapping("/player/{playerId}")
    List<TrainingSession> getSessionsByPlayerId(@PathVariable("playerId") int playerId);

    @GetMapping("/coach/{coachId}")
    List<TrainingSession> getSessionsByCoachId(@PathVariable("coachId") int coachId);
    
    @PostMapping
    TrainingSession createTrainingSession(@RequestBody TrainingSession session) ;
        
}
