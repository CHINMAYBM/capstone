package com.ustcapstone.atheleteservice.model;
import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "playerGoals")
public class PlayerGoal {

    @Id
    private int goalId;    
    private int playerId; 
    private int coachId;            
    private String goalType;               // Speed", "Strength"
    private String goalDescription;      
    private float targetValue;  
    private String deadline;
    private float achievedValue = 0.0f;  // Default value (will be updated later)
    private String status = "Not Started";  // Default value (will be updated later)
    private String feedbackRemarks = "";
    
    
    public int getGoalId() {
		return goalId;
	}
	public void setGoalId(int goalId) {
		this.goalId = goalId;
	}
	public int getPlayerId() {
		return playerId;
	}
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	public String getGoalType() {
		return goalType;
	}
	public void setGoalType(String goalType) {
		this.goalType = goalType;
	}
	public String getGoalDescription() {
		return goalDescription;
	}
	public void setGoalDescription(String goalDescription) {
		this.goalDescription = goalDescription;
	}
	public float getTargetValue() {
		return targetValue;
	}
	public void setTargetValue(float targetValue) {
		this.targetValue = targetValue;
	}
	public float getAchievedValue() {
		return achievedValue;
	}
	public void setAchievedValue(float achievedValue) {
		this.achievedValue = achievedValue;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFeedbackRemarks() {
		return feedbackRemarks;
	}
	public void setFeedbackRemarks(String feedbackRemarks) {
		this.feedbackRemarks = feedbackRemarks;
	}
	public int getCoachId() {
		return coachId;
	}
	public void setCoachId(int coachId) {
		this.coachId = coachId;
	}
	public String getDeadline() {
		return deadline;
	}
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
	
    

   
}

