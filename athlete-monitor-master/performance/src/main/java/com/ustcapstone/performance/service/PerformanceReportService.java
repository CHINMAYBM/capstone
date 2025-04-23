package com.ustcapstone.performance.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ustcapstone.performance.model.PerformanceReport;
import com.ustcapstone.performance.repository.PerformanceReportRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PerformanceReportService {

    @Autowired
    private PerformanceReportRepository repository;

    public PerformanceReport savePerformanceReport(PerformanceReport report) {
        return repository.save(report);
    }

    public List<PerformanceReport> getAllReports() {
        return repository.findAll();
    }

   
    public PerformanceReport getReportById(String id) {
        Optional<PerformanceReport> report = repository.findById(id);
        return report.orElse(null);
    }

  
    public boolean deleteReportById(String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
    public List<PerformanceReport> getReportsByPlayerId(int playerId) {
        return repository.findByPlayerId(playerId);
    }

   
    public List<PerformanceReport> getReportsByPlayerName(String playerName) {
        return repository.findByPlayerName(playerName);
    }

    public String generatePerformanceRemark(PerformanceReport report) {
        StringBuilder remark = new StringBuilder();

        // HRV
        if (report.getHrv() > 80) {
            remark.append("HRV: Elite; ");
        } else if (report.getHrv() >= 40) {
            remark.append("HRV: Professional; ");
        } else {
            remark.append("HRV: Amateur; ");
        }

        // Top Speed
        if (report.getTopSpeed() > 40) {
            remark.append("Top Speed: Elite; ");
        } else if (report.getTopSpeed() >= 20) {
            remark.append("Top Speed: Professional; ");
        } else {
            remark.append("Top Speed: Amateur; ");
        }

        // Calories Burned
        if (report.getCaloriesBurned() > 1500) {
            remark.append("Calories Burned: Elite; ");
        } else if (report.getCaloriesBurned() >= 800) {
            remark.append("Calories Burned: Professional; ");
        } else {
            remark.append("Calories Burned: Amateur; ");
        }

        // Passing Accuracy
        if (report.getPassingAccuracy() > 85) {
            remark.append("Passing Accuracy: Elite; ");
        } else if (report.getPassingAccuracy() >= 75) {
            remark.append("Passing Accuracy: Professional; ");
        } else {
            remark.append("Passing Accuracy: Amateur; ");
        }

        // Dribbling Success Rate
        if (report.getDribblingSuccessRate() > 60) {
            remark.append("Dribbling Success Rate: Elite; ");
        } else if (report.getDribblingSuccessRate() >= 50) {
            remark.append("Dribbling Success Rate: Professional; ");
        } else {
            remark.append("Dribbling Success Rate: Amateur; ");
        }

        // Shooting Accuracy
        if (report.getShootingAccuracy() > 55) {
            remark.append("Shooting Accuracy: Elite; ");
        } else if (report.getShootingAccuracy() >= 45) {
            remark.append("Shooting Accuracy: Professional; ");
        } else {
            remark.append("Shooting Accuracy: Amateur; ");
        }

        // Tackling Success Rate
        if (report.getTacklingSuccessRate() > 70) {
            remark.append("Tackling Success Rate: Elite; ");
        } else if (report.getTacklingSuccessRate() >= 60) {
            remark.append("Tackling Success Rate: Professional; ");
        } else {
            remark.append("Tackling Success Rate: Amateur; ");
        }

        // Crossing Accuracy
        if (report.getCrossingAccuracy() > 25) {
            remark.append("Crossing Accuracy: Elite; ");
        } else if (report.getCrossingAccuracy() >= 15) {
            remark.append("Crossing Accuracy: Professional; ");
        } else {
            remark.append("Crossing Accuracy: Amateur; ");
        }

        return remark.toString();
    }
}
