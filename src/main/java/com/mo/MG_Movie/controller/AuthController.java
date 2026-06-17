package com.mo.MG_Movie.controller;

import com.mo.MG_Movie.DTOs.LoginRequest;
import com.mo.MG_Movie.DTOs.RegisterRequest;
import com.mo.MG_Movie.DTOs.UserResponseDTO;
import com.mo.MG_Movie.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody RegisterRequest request) {
        UserResponseDTO response = userService.registerUser(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@RequestBody LoginRequest request) {
        UserResponseDTO response = userService.loginUser(request);
        return ResponseEntity.ok(response);
    }
}