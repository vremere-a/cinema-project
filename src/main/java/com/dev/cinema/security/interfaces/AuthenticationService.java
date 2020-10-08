package com.dev.cinema.security.interfaces;

import com.dev.cinema.exeptions.AuthenticationException;
import com.dev.cinema.model.User;

public interface AuthenticationService {
    User login(String email, String password) throws AuthenticationException;

    User register(String email, String password);
}
