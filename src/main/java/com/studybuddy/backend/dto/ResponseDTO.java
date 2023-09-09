package com.studybuddy.backend.dto;

public class ResponseDTO {
    private boolean success;
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResponseDTO() {
        // No-argument constructor for easier instantiation
    }
    public ResponseDTO(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}