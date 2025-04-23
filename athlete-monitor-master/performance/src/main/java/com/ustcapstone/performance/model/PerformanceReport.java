package com.ustcapstone.performance.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "performance_reports")
public class PerformanceReport {

    @Id
    private String id; // MongoDB will automatically generate this ID

    private int playerId;
    private int height;
    private int weight;
    private String playerName;
    private String recordDate; // When the performance was recorded

    private int hrv; // Heart Rate Variability
    private int topSpeed; // Top Speed
    private int caloriesBurned; // Calories Burned

    // Soccer (Football) Attributes
    private double passingAccuracy;
    private double dribblingSuccessRate;
    private double shootingAccuracy;
    private double tacklingSuccessRate;
    private double crossingAccuracy;
}
