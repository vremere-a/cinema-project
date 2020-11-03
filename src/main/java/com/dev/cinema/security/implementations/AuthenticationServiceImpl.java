package com.dev.cinema.security.implementations;

import com.dev.cinema.model.User;
import com.dev.cinema.security.interfaces.AuthenticationService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;

    @Autowired
    public AuthenticationServiceImpl(UserService userService,
                                     ShoppingCartService shoppingCartService) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public User register(String email, String password) {
        User user = new User(email, password);
        User userFromDB = userService.add(user);
        shoppingCartService.registerNewShoppingCart(userFromDB);
        return userFromDB;
    }
}
