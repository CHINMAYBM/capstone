package com.ustcapstone.trainingsession.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Document(collection = "trainingSessions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainingSession {
    @Id
    private int sessionId;
    private int coachId;
    private List<Integer> playerIds; 
    private String date;
    private String duration;
   

    // Getters and Setters
}