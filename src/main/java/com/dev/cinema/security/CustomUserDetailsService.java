package com.dev.cinema.security;

import com.dev.cinema.model.User;
import com.dev.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User.UserBuilder;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userOptional = userService.findByEmail(email);
        UserBuilder builder = null;
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            builder = org.springframework.security.core.userdetails.User.withUsername(user.getEmail());
            builder.password(user.getPassword());
            String[] roles = user.getUserRole()
                    .stream()
                    .map(role -> role.getRoleName().toString())
                    .toArray(String[]::new);

            builder.roles(roles);
            return builder.build();
        } else {
            throw new UsernameNotFoundException("User with " + email + "email, not found");
        }
    }
}
