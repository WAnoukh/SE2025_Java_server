package com.example.aidsystem.controller;

public class LoginRequest {
    private String username;
    private String password;

    // Конструкторы, геттеры и сеттеры
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
