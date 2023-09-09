package com.studybuddy.backend.dto;

public class StudentLoginRequest {
    private String email;
    private final String password;

    public StudentLoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}