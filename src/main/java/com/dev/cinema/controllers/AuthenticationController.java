package com.dev.cinema.controllers;

import com.dev.cinema.model.dto.UserRequestDto;
import com.dev.cinema.security.interfaces.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    private final AuthenticationService service;
    @Autowired
    public AuthenticationController(AuthenticationService service) {
        this.service = service;
    }

    @PostMapping("/registration")
    public void registration(@RequestBody UserRequestDto requestDto) {
        service.register(requestDto.getEmail(),requestDto.getPassword());
    }
}
