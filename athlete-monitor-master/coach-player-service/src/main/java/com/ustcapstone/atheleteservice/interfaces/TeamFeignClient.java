package com.ustcapstone.atheleteservice.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ustcapstone.atheleteservice.model.Player;
import com.ustcapstone.atheleteservice.model.Team;

import java.util.List;

@FeignClient(name = "teams-service", url = "http://localhost:8091/api/teams") // Replace with TeamService URL
public interface TeamFeignClient{

    @GetMapping("/by-coach/{coachId}")
    List<Team> getTeamsByCoachId(@PathVariable("coachId") int coachId);
    
    @GetMapping("/teams/player/{playerId}")
    Team getTeamByPlayerId(@PathVariable int playerId);
    @GetMapping("/{teamId}")
    Team getTeamById(@PathVariable int teamId);
    @PostMapping
    Team createTeam(@RequestBody Team team);
 
    
    
    //added by me
    @GetMapping("/team/{teamId}/players")
    List<Player> getPlayersByTeamId(@PathVariable("teamId") int teamId);
    //added by me
}
