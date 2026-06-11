package com.mo.MG_Movie.DTOs;

import lombok.Data;

@Data
public class UserResponseDTO {
    private Long userId;
    private String username;
    private String email;
    private String avatarUrl;
    private String role;
    private String token;
}
