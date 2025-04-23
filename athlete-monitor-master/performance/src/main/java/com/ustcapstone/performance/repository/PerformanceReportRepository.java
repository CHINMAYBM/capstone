package com.ustcapstone.performance.repository;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ustcapstone.performance.model.PerformanceReport;

@Repository
public interface PerformanceReportRepository extends MongoRepository<PerformanceReport, String> {
	List<PerformanceReport> findByPlayerId(int playerId);
    List<PerformanceReport> findByPlayerName(String playerName);
}
