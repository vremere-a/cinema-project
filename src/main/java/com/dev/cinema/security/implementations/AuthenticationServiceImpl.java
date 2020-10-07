package com.dev.cinema.security.implementations;

import com.dev.cinema.exeptions.AuthenticationException;
import com.dev.cinema.library.Inject;
import com.dev.cinema.library.Service;
import com.dev.cinema.model.User;
import com.dev.cinema.security.interfaces.AuthenticationService;
import com.dev.cinema.service.UserService;
import com.dev.cinema.util.HashUtil;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;

    @Override
    public User login(String email, String password) throws AuthenticationException {
        Optional<User> userFromDB = userService.findByEmail(email);
        if (userFromDB.isPresent()
                && userFromDB.get().getPassword().equals(HashUtil.hashPassword(password,
                userFromDB.get().getSalt()))) {
            return userFromDB.get();
        }
        throw new AuthenticationException("Incorrect username or password");
    }

    @Override
    public User register(String email, String password) {
        User user = new User(email, password);
        return userService.add(user);
    }
}
