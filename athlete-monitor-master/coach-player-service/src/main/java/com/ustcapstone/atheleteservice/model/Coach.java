package com.ustcapstone.atheleteservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "coaches")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Coach {
 @Id
    private int coachId;  
    private String name;
    private String sport;
    private List<Integer> teamIds;  
    private int age;
    private String email;

}
