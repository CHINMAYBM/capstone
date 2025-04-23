package com.example.team_service.exception;

public class TeamDeletionException extends RuntimeException {
    public TeamDeletionException(String message) {
        super(message);
    }
}