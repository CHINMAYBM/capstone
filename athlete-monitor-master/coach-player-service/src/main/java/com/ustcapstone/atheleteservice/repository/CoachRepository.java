package com.ustcapstone.atheleteservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ustcapstone.atheleteservice.model.Coach;

public interface CoachRepository extends MongoRepository<Coach, Integer> {
	 
	
	Optional<Coach> findByEmail(String email);
}