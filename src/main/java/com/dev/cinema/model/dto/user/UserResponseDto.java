package com.dev.cinema.model.dto.user;

import lombok.Data;

@Data
public class UserResponseDto {
    private Long id;
    private String password;
    private String email;
}
