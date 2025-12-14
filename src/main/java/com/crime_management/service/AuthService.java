package com.crime_management.service;


import com.crime_management.dto.auth.AuthResponse;
import com.crime_management.dto.auth.LoginRequest;
import com.crime_management.dto.auth.RegisterRequest;

public interface AuthService {
    AuthResponse login(LoginRequest request);
    AuthResponse register(RegisterRequest request);
}
