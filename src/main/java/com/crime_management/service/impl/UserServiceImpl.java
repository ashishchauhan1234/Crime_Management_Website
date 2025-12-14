package com.crime_management.service.impl;

import com.crime_management.exception.ResourceNotFoundException;
import com.crime_management.repository.UserRepository;
import com.crime_management.dto.user.UserDTO;
import com.crime_management.entity.User;
import com.crime_management.mapper.UserMapper;
import com.crime_management.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDTO getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return userMapper.toDto(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }
}
