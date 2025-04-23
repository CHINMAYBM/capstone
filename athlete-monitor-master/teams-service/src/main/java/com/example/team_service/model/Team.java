package com.example.team_service.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "teams")
@Data
public class Team {
    @Id
    private int teamId;
    private String name;
    private String sportCategory;
    private int coachId; 
    private List<Integer> playerIds;// Coach responsible for this team
}
