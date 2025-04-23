package com.ustcapstone.atheleteservice.repository;




import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ustcapstone.atheleteservice.model.Player;

@Repository
public interface AthleteRepository extends MongoRepository<Player,Integer> {
    // Custom query methods can be defined here if needed
	
	
	//added by me
	List<Player> findByTeamId(int teamId);

	List<Player> findAllByPlayerIdIn(List<Integer> playerIds);
	
	Optional<Player> findByEmail(String email);

}

