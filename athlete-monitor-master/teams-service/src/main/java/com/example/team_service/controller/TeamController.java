package com.example.team_service.controller;


import com.example.team_service.interfaces.AthleteFeignClient;

import com.example.team_service.model.Player;
import com.example.team_service.model.Team;
import com.example.team_service.service.TeamService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
//@CrossOrigin(origins = "http://localhost:4200")
public class TeamController {

    private final TeamService teamService;
    //added by me
    
    @GetMapping("/team/{teamId}/players")
    public ResponseEntity<List<Player>> getPlayersByTeamId(@PathVariable int teamId) {
        List<Player> players = teamService.getPlayersByTeamId(teamId);
        return ResponseEntity.ok(players);
    }
    @GetMapping("/{teamId}/player-ids")
    public ResponseEntity<List<Integer>> getPlayerIdsByTeamId(@PathVariable int teamId) {
        List<Integer> playerIds = teamService.getPlayerIdsByTeamId(teamId);
        return ResponseEntity.ok(playerIds);
    }
    //me
    
    @GetMapping("/by-coach/{coachId}")
    public ResponseEntity<List<Team>> getTeamsByCoachId(@PathVariable int coachId) {
        List<Team> teams = teamService.getTeamsByCoachId(coachId);
        return ResponseEntity.ok(teams);
    }

   
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }
   

    // Create a new team
    @PostMapping
    public ResponseEntity<Team> createTeam(@RequestBody Team team) {
        Team savedTeam = teamService.createTeam(team);
        return new ResponseEntity<>(savedTeam, HttpStatus.CREATED);
    }

    // Get a team by ID
    @GetMapping("/{teamId}")
    public ResponseEntity<Team> getTeamById(@PathVariable int teamId) {
        Team team = teamService.getTeamById(teamId);
        return new ResponseEntity<>(team, HttpStatus.OK);
    }

    // Update an existing team
    @PutMapping("/{teamId}")
    public ResponseEntity<Team> updateTeam(@PathVariable int teamId, @RequestBody Team updatedTeam) {
        Team team = teamService.updateTeam(teamId, updatedTeam);
        return new ResponseEntity<>(team, HttpStatus.OK);
    }

    // Delete a team
    @DeleteMapping("/{teamId}")
    public ResponseEntity<Void> deleteTeam(@PathVariable int teamId) {
    	List<Integer> playerIds = teamService.getPlayerIdsByTeamId(teamId);
        teamService.deleteTeam(teamId,playerIds);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Get all teams
    @GetMapping
    public ResponseEntity<List<Team>> getAllTeams() {
    	
        List<Team> teams = teamService.getAllTeams();
        return new ResponseEntity<>(teams, HttpStatus.OK);
       
    }
    

    // Find a team by name
    @GetMapping("/name/{teamName}")
    public ResponseEntity<Team> getTeamByName(@PathVariable String teamName) {
        Team team = teamService.getTeamByName(teamName);
        return new ResponseEntity<>(team, HttpStatus.OK);
    }

    // Count total teams
    @GetMapping("/count")
    public ResponseEntity<Long> countTeams() {
        long count = teamService.countTeams();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    // Find teams by sport category
    @GetMapping("/sport/{sportCategory}")
    public ResponseEntity<List<Team>> findTeamsBySportCategory(@PathVariable String sportCategory) {
        List<Team> teams = teamService.findTeamsBySportCategory(sportCategory);
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }
    
    @Autowired
    private AthleteFeignClient athleteFeignClient;
    @GetMapping("/teams/unassigned-players")
    public ResponseEntity<List<Player>> getUnassignedPlayersForTeamCreation() {
        List<Player> unassignedPlayers = athleteFeignClient.getUnassignedPlayers();
        return ResponseEntity.ok(unassignedPlayers);
    }
    
    
}

