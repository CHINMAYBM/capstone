package com.example.team_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.team_service.exception.TeamCreationException;
import com.example.team_service.exception.TeamDeletionException;
import com.example.team_service.interfaces.AthleteFeignClient;

import com.example.team_service.model.Player;
import com.example.team_service.model.Team;
import com.example.team_service.repository.TeamRepository;

@Service
public class TeamService {
    private final TeamRepository teamRepository;
    private final AthleteFeignClient athleteFeignClient;
   

   
    public TeamService(TeamRepository teamRepository, AthleteFeignClient athleteFeignClient) {
        this.teamRepository = teamRepository;
        this.athleteFeignClient = athleteFeignClient;
        
    }
    public List<Integer> getPlayerIdsByTeamId(int teamId) {
        return teamRepository.findByTeamId(teamId)
            .map(Team::getPlayerIds)
            .orElseThrow(() -> new RuntimeException("Team not found with ID: " + teamId));
    }

    // Get players by team ID
    public List<Player> getPlayersByTeamId(int teamId) {
        return athleteFeignClient.getPlayersByTeamId(teamId);
    }

    // Get teams by coach ID
    public List<Team> getTeamsByCoachId(int coachId) {
        return teamRepository.findByCoachId(coachId);
    }

    // Get team by ID
    public Team getTeamById(int teamId) {
        return teamRepository.findById(teamId)
            .orElseThrow(() -> new RuntimeException("Team not found"));
    }

    // Get all teams
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    // Find a team by name
    public Team getTeamByName(String teamName) {
        return teamRepository.findByName(teamName)
            .orElseThrow(() -> new RuntimeException("Team not found with name: " + teamName));
    }

    // Check if a team exists
    public boolean doesTeamExist(int teamId) {
        return teamRepository.existsById(teamId);
    }

    // Count total teams
    public long countTeams() {
        return teamRepository.count();
    }

    // Find teams by sport category
    public List<Team> findTeamsBySportCategory(String sportCategory) {
        return teamRepository.findBySportCategory(sportCategory);
    }

    // Create a new team
  
  
    
    @Transactional
    public Team createTeam(Team team) {

    	long playerCount = teamRepository.count(); 
        int nextPlayerId = (int) (playerCount + 1);  
        team.setTeamId(nextPlayerId);
        Team createdTeam = teamRepository.save(team);
        updateCoachTeams(team.getCoachId(), createdTeam.getTeamId(), true);
        updatePlayersTeamId(createdTeam.getTeamId(), team.getPlayerIds());
        return createdTeam;

    }

    // Update an existing team
    @Transactional
    public Team updateTeam(int teamId, Team updatedTeam) {
        Team existingTeam = teamRepository.findById(teamId)
            .orElseThrow(() -> new RuntimeException("Team not found for ID: " + teamId));

        // Update team properties
        existingTeam.setName(updatedTeam.getName());
        existingTeam.setSportCategory(updatedTeam.getSportCategory());
        existingTeam.setCoachId(updatedTeam.getCoachId());
        existingTeam.setPlayerIds(updatedTeam.getPlayerIds()); // Update player IDs

        // Save the updated team
        teamRepository.save(existingTeam);
        
        // Update coach's team IDs
        updateCoachTeams(existingTeam.getCoachId(), teamId, false);
        
        // Update players' teamId
        updatePlayersTeamId(existingTeam.getTeamId(), updatedTeam.getPlayerIds());

        return existingTeam;
    }

    // Delete a team
    
//    @Transactional
//    public void deleteTeam(int teamId) {
//        Team team = teamRepository.findById(teamId)
//                .orElseThrow(() -> new RuntimeException("Team not found for ID: " + teamId));
//
//        try {
//            athleteFeignClient.removePlayersTeamId(teamId);
//            updateCoachTeams(team.getCoachId(), teamId, false);
//            teamRepository.delete(team);
//        } catch (Exception e) {
//            throw new TeamDeletionException("Failed to delete team with ID: " + teamId);
//        }
//    }
    
    
    
    
    @Transactional
    public void deleteTeam(int teamId,List<Integer> playerIds) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new RuntimeException("Team not found for ID: " + teamId));


       
            

        try {
            // Remove players' team IDs
            athleteFeignClient.removePlayersTeamId(teamId,playerIds);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update player team IDs: " + e.getMessage());
        }

        try {
            // Update the coach's team list

            updateCoachTeams(team.getCoachId(), teamId, false);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update coach teams: " + e.getMessage());
        }

        try {
            // Delete the team
            teamRepository.delete(team);

       

        } catch (Exception e) {
            throw new RuntimeException("Failed to delete team: " + e.getMessage());
        }

    }


    // Helper method to update players' teamId
    private void updatePlayersTeamId(int teamId, List<Integer> playerIds) {
        athleteFeignClient.updatePlayersTeamId(teamId, playerIds);
    }

    // Helper method to update coach's teamIds
    private void updateCoachTeams(int coachId, int teamId, boolean isAdding) {
        List<Integer> currentTeamIds = athleteFeignClient.getTeamIdsByCoachId(coachId);
        
        if (isAdding) {
            if (!currentTeamIds.contains(teamId)) {
                currentTeamIds.add(teamId); // Add teamId if creating a new team
            }
        } else {
            currentTeamIds.remove(Integer.valueOf(teamId)); // Remove teamId if deleting/updating a team
        }
        
        athleteFeignClient.updateCoachTeamIds(coachId, currentTeamIds);
    }
   
}

