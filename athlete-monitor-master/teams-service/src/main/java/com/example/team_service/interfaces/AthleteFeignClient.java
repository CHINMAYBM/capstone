package com.example.team_service.interfaces;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.team_service.model.Player;
@FeignClient(name="coach-player-service",url="http://localhost:8090/api/athletes")
public interface AthleteFeignClient {
	
	@GetMapping("/team/{teamId}/players")
    List<Player> getPlayersByTeamId(@PathVariable("teamId") int teamId);
	@PutMapping("/update-team/{teamId}")
    void updatePlayersTeamId(@PathVariable("teamId") int teamId, List<Integer> playerIds);
	@PutMapping("/remove-team/{teamId}")
	void removePlayersTeamId(@PathVariable("teamId") int teamId, @RequestParam("playerIds") List<Integer> playerIds);

    @GetMapping("/teamIds/{coachId}")
    List<Integer> getTeamIdsByCoachId(@PathVariable("coachId") int coachId);

    @PutMapping("/update-teams/{coachId}")
    void updateCoachTeamIds(@PathVariable("coachId") int coachId, @RequestBody List<Integer> teamIds);
    
    @GetMapping("/players/unassigned")
    List<Player> getUnassignedPlayers();
}
