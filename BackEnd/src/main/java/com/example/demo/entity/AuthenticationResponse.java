package com.example.demo.entity;


public class AuthenticationResponse {

    private String username;
    private String token;
    private Role role;

    public AuthenticationResponse() {
    }

    public AuthenticationResponse(String username, String token) {
        this.username = username;
        this.token = token;
    }

    public AuthenticationResponse(String username, String token, Role role) {
        this.username = username;
        this.token = token;
        this.role = role;
    }

    public AuthenticationResponse(String username, Role role) {
        this.username = username;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "AuthenticationResponse{" +
                "username='" + username + '\'' +
                ", token='" + token + '\'' +
                ", role=" + role +
                '}';
    }
}
