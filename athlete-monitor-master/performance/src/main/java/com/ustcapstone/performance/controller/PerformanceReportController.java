package com.ustcapstone.performance.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ustcapstone.performance.model.PerformanceReport;
import com.ustcapstone.performance.model.PerformanceReportWithRemarks;
import com.ustcapstone.performance.service.PerformanceReportService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/performance-reports")
public class PerformanceReportController {

    @Autowired
    private PerformanceReportService service;

   
    @PostMapping
    public ResponseEntity<PerformanceReport> savePerformanceReport(@RequestBody PerformanceReport report) {
        PerformanceReport savedReport = service.savePerformanceReport(report);
        return ResponseEntity.ok(savedReport);
    }

    @GetMapping
    public ResponseEntity<List<PerformanceReport>> getAllReports() {
        List<PerformanceReport> reports = service.getAllReports();
        return ResponseEntity.ok(reports);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PerformanceReport> getReportById(@PathVariable String id) {
        PerformanceReport report = service.getReportById(id);
        if (report != null) {
            return ResponseEntity.ok(report);
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReportById(@PathVariable String id) {
        boolean isDeleted = service.deleteReportById(id);
        if (isDeleted) {
            return ResponseEntity.ok("Performance report deleted successfully.");
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/remarks")
    public ResponseEntity<String> generatePerformanceRemark(@PathVariable String id) {
        PerformanceReport report = service.getReportById(id);
        if (report != null) {
            String remarks = service.generatePerformanceRemark(report);
            return ResponseEntity.ok(remarks);
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/player/{playerId}/remarks")
    public ResponseEntity<List<String>> generatePerformanceRemarksByPlayerId(@PathVariable int playerId) {
        List<PerformanceReport> reports = service.getReportsByPlayerId(playerId);
        if (!reports.isEmpty()) {
            List<String> remarks = reports.stream()
                                          .map(service::generatePerformanceRemark)
                                          .toList();
            return ResponseEntity.ok(remarks);
        }
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/player/{playerId}")
    public List<PerformanceReport> getReportsByPlayerId(@PathVariable int playerId) {
        return service.getReportsByPlayerId(playerId);
    }

    @GetMapping("/player/name/{playerName}")
    public List<PerformanceReport> getReportsByPlayerName(@PathVariable String playerName) {
        return service.getReportsByPlayerName(playerName);
    }
    
    

        @GetMapping("/player/name/{playerName}/with-remarks")
        public ResponseEntity<List<PerformanceReportWithRemarks>> getReportsWithRemarksByPlayerName(@PathVariable String playerName) {
            List<PerformanceReport> reports = service.getReportsByPlayerName(playerName);
            if (reports.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            List<PerformanceReportWithRemarks> response = reports.stream()
                .map(report -> {
                    String remarks = service.generatePerformanceRemark(report);
                    return new PerformanceReportWithRemarks(report, remarks);
                })
                .collect(Collectors.toList());

            return ResponseEntity.ok(response);
        }
        
    
}