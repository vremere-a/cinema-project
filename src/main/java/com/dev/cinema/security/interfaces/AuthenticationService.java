package com.dev.cinema.security.interfaces;

import com.dev.cinema.model.User;

public interface AuthenticationService {

    User register(String email, String password);
}
