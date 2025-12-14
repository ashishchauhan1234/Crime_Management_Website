package com.crime_management.service;

import com.crime_management.dto.user.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO getUser(Long id);
    List<UserDTO> getAllUsers();
}
