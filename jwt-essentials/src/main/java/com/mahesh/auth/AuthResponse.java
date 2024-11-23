package com.mahesh.auth;

public class AuthResponse {
    
    private String token;

    // Constructor
    public AuthResponse(String token) {
        this.token = token;
    }

    // Getter for the token
    public String getToken() {
        return token;
    }

    // Optional: Override toString for better debugging
    @Override
    public String toString() {
        return "AuthResponse{" +
                "token='" + token + '\'' +
                '}';
    }
}
