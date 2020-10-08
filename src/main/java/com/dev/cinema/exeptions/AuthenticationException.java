package com.dev.cinema.exeptions;

public class AuthenticationException extends Exception {
    public AuthenticationException(String message) {
        super(message);
    }
}
