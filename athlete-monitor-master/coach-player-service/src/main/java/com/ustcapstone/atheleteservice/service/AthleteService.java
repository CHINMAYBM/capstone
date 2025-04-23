package com.ustcapstone.atheleteservice.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.ustcapstone.atheleteservice.exception.CoachCreationException;
import com.ustcapstone.atheleteservice.exception.PlayerCreationException;
import com.ustcapstone.atheleteservice.interfaces.TeamFeignClient;
import com.ustcapstone.atheleteservice.interfaces.TrainingSessionFeignClient;
import com.ustcapstone.atheleteservice.model.Coach;
import com.ustcapstone.atheleteservice.model.Player;
import com.ustcapstone.atheleteservice.model.Team;
import com.ustcapstone.atheleteservice.model.TrainingSession;

import com.ustcapstone.atheleteservice.repository.AthleteRepository;
import com.ustcapstone.atheleteservice.repository.CoachRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AthleteService {

    @Autowired
    private AthleteRepository athleteRepository;

    @Autowired
    private CoachRepository coachRepository;
    @Autowired
    private  TeamFeignClient teamFeignClient;
    
    @Autowired  // Injects TrainingSessionFeignClient into AthleteService
    private TrainingSessionFeignClient trainingSessionFeignClient;
    
 
 

    
    
   
    public AthleteService(TeamFeignClient teamFeignClient) {
        this.teamFeignClient = teamFeignClient;
    }
    //added by me
    public List<Integer> getTeamIdsByCoachId(int coachId) {
        Coach coach = coachRepository.findById(coachId)
                .orElseThrow(() -> new RuntimeException("Coach not found for ID: " + coachId));
        return coach.getTeamIds(); // Return the list of team IDs
    }
    public Team createTeam(Team team) {
    	return teamFeignClient.createTeam(team);
    }
    public void updateTeamIds(int coachId, List<Integer> teamIds) {
        Coach coach = coachRepository.findById(coachId)
                .orElseThrow(() -> new RuntimeException("Coach not found for ID: " + coachId));
        coach.setTeamIds(teamIds); // Update the list of team IDs
        coachRepository.save(coach); // Save the changes
    }
   
        public List<Player> getPlayersByTeamId(int teamId) {
            return athleteRepository.findByTeamId(teamId);
        }
        
        public List<Player> getPlayersByPlayerId(int playerId) {
            // Fetch the player using the playerId
            Player player = athleteRepository.findById(playerId).orElse(null);
            if (player != null) {
                int teamId = player.getTeamId(); // Retrieve the teamId
                if (teamId != 0) { // Assuming that 0 means no team
                    // If the player has a valid teamId, fetch all players in that team
                    return athleteRepository.findByTeamId(teamId);
                }
            }
            return List.of(); // Return an empty list if no valid teamId found
        }
    
    //me
        public void updatePlayersTeamId(int teamId, List<Integer> playerIds) {
            List<Player> players = athleteRepository.findAllById(playerIds);
            for (Player player : players) {
                player.setTeamId(teamId); // Update teamId for each player
                athleteRepository.save(player); // Save updated player
            }
        }
 
        public void removePlayersTeamId(int teamId, List<Integer> playerIds) {
        	List<Player> players = athleteRepository.findAllByPlayerIdIn(playerIds);

            for (Player player : players) {
                player.setTeamId(0); // Update teamId for each player
                athleteRepository.save(player); // Save updated player
            }
        }
        
     // Add this method to your AthleteService class
        public void updatePlayerWeight(int playerId, int weight) {
            Player player = athleteRepository.findById(playerId)
                    .orElseThrow(() -> new RuntimeException("Player not found for ID: " + playerId));
            player.setWeight(weight); // Update the weight
            athleteRepository.save(player); // Save changes
        }

   
       

    public List<Team> getTeamsByCoachId(int coachId) {
        return teamFeignClient.getTeamsByCoachId(coachId);
    }

    // Player CRUD Operations
//    public Player createPlayer(Player player) {
//    	long playerCount = athleteRepository.count(); 
//        int nextPlayerId = (int) (playerCount + 1);  
//        player.setPlayerId(nextPlayerId);
//        return athleteRepository.save(player);
//    }
    public Player createPlayer(Player player) {
        try {
          
            long playerCount = athleteRepository.count();
            int nextPlayerId = (int) (playerCount + 1);
            player.setPlayerId(nextPlayerId);
            
            // Saving the player to the database
            return athleteRepository.save(player);
        } catch (Exception e) {
            // If there is any issue, throw a custom exception
            throw new PlayerCreationException("Error creating player: " + e.getMessage());
        }
    }

    public List<Player> getAllPlayers() {
        return athleteRepository.findAll();
    }

    public Player getPlayerById(int id) {
        return athleteRepository.findById(id).orElse(null);
    }

    public Player updatePlayer(Player player) {
        return athleteRepository.save(player);
    }

    public void deletePlayer(int id) {
        athleteRepository.deleteById(id);
    }

    // Coach CRUD Operations
//    public Coach createCoach(Coach coach) {
//    	long coachCount = coachRepository.count(); 
//        int nextCoachId = (int) (coachCount + 1);  
//        coach.setCoachId(nextCoachId);
//        return coachRepository.save(coach);
//    }
    public Coach createCoach(Coach coach) {
        try {
            // Simulating logic to calculate next coach ID
            long coachCount = coachRepository.count();
            int nextCoachId = (int) (coachCount + 1);
            coach.setCoachId(nextCoachId);
            
            // Saving the coach to the database
            return coachRepository.save(coach);
        } catch (Exception e) {
            // If there is any issue, throw a custom exception
            throw new CoachCreationException("Error creating coach: " + e.getMessage());
        }
    }

    public List<Coach> getAllCoaches() {
        return coachRepository.findAll();
    }

    public Coach getCoachById(int id) {
        return coachRepository.findById(id).orElse(null);
    }

    public Coach updateCoach(Coach coach) {
        return coachRepository.save(coach);
    }

    public void deleteCoach(int id) {
        coachRepository.deleteById(id);
    }
    
//   public List<TrainingSession> getTrainingSessionsByPlayerId(int playerId) {
//	   return trainingSessionFeignClient.getSessionsByPlayerId(playerId);
//   }
    
    public List<TrainingSession> getTrainingSessionsByPlayerId(int playerId) {
        // Fetch training sessions using Feign client
        List<TrainingSession> sessions = trainingSessionFeignClient.getSessionsByPlayerId(playerId);

        // Populate playernames for each session
        for (TrainingSession session : sessions) {
            if (session.getPlayerIds() != null) {
                // Fetch names for each playerId
                List<String> names = session.getPlayerIds().stream()
                    .map(athleteRepository::findById)
                    .filter(Optional::isPresent)
                    .map(player -> player.get().getName()) // Assuming the Player entity has a `getName()` method
                    .collect(Collectors.toList());

                // Set the playernames in the session
                session.setPlayernames(names);
            }
        }

        return sessions;
    }

   
    public List<TrainingSession> getTrainingSessionsByCoachId(int coachId) {
        return trainingSessionFeignClient.getSessionsByCoachId(coachId);
    }
    
    public int getTeamIdByPlayerId(int playerId) {
        Player player = athleteRepository.findById(playerId)
            .orElseThrow(() -> new RuntimeException("Player not found for player ID: " + playerId));
        return player.getTeamId();
    }
    public List<Player> getUnassignedPlayers() {
        return athleteRepository.findByTeamId(0);
    }
    
    public Coach getCoachForPlayer(int playerId) {
        // Fetch the Player from player-coach service by playerId
        Player player = athleteRepository.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Player not found"));

        // Fetch the Team using teamId from the Player
        int teamId = player.getTeamId();
        Team team = teamFeignClient.getTeamById(teamId);  // Call Team microservice

        // Get the coachId from the Team
        int coachId = team.getCoachId();
        
        // Fetch the Coach using coachId
        return coachRepository.findById(coachId)
                .orElseThrow(() -> new RuntimeException("Coach not found"));
    }
    public String getPlayerNameById(int playerId) {
        Optional<Player> player = athleteRepository.findById(playerId);
        return player.map(Player::getName).orElse(null);
    }
}
