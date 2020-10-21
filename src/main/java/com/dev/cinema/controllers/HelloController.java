package com.dev.cinema.controllers;

import lombok.extern.log4j.Log4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String sayHello() {
        return "hello";
    }
}
