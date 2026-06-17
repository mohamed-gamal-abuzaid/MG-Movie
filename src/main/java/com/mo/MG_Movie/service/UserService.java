package com.mo.MG_Movie.service;

import com.mo.MG_Movie.DTOs.LoginRequest;
import com.mo.MG_Movie.DTOs.RegisterRequest;
import com.mo.MG_Movie.DTOs.UserResponseDTO;
import com.mo.MG_Movie.model.User;
import com.mo.MG_Movie.Repository.UserRepository;
import com.mo.MG_Movie.security.JwtUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }


    public UserResponseDTO registerUser(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        User savedUser = userRepository.save(user);


        String token = jwtUtils.generateJwtToken(savedUser.getEmail());
        UserResponseDTO responseDTO = mapToDTO(savedUser);
        responseDTO.setToken(token);

        return responseDTO;
    }


    public UserResponseDTO loginUser(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Email or password incorrect"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Email or password incorrect");
        }

        if ("BANNED".equals(String.valueOf(user.getStatus()))) {
            throw new RuntimeException("This account has been banned!");
        }


        String token = jwtUtils.generateJwtToken(user.getEmail());
        UserResponseDTO responseDTO = mapToDTO(user);
        responseDTO.setToken(token);

        return responseDTO;
    }

    private UserResponseDTO mapToDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setUserId(user.getUserid());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setRole(String.valueOf(user.getRole()));
        dto.setAvatarUrl(user.getAvatarUrl());
        return dto;
    }
}