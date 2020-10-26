package com.dev.cinema.model.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class UserRequestDto {
    private String name;
    private String password;
    private String email;
}
