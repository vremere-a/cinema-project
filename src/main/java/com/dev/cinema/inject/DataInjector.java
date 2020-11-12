package com.dev.cinema.inject;

import com.dev.cinema.model.Role;
import com.dev.cinema.model.User;
import com.dev.cinema.service.RoleService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataInjector {
    private static final String USER_ROLE_NAME = "USER";
    private static final String ADMIN_ROLE_NAME = "ADMIN";

    private final RoleService roleService;
    private final UserService userService;
    private final ShoppingCartService cartService;

    @Autowired
    public DataInjector(RoleService roleService,
                        UserService userService,
                        ShoppingCartService cartService) {
        this.roleService = roleService;
        this.userService = userService;
        this.cartService = cartService;
    }

    @PostConstruct
    public void injectData() {
        Role userRole = Role.of(USER_ROLE_NAME);
        Role adminRole = Role.of(ADMIN_ROLE_NAME);
        roleService.add(userRole);
        roleService.add(adminRole);

        User admin = new User();
        admin.setEmail("a@ukr.net");
        admin.setPassword("qwer");
        admin.setUserRoles(Set.of(adminRole));
        userService.add(admin);

        User user = new User();
        user.setEmail("u@ukr.net");
        user.setPassword("qwer");
        user.setUserRoles(Set.of(userRole));
        userService.add(user);
        cartService.registerNewShoppingCart(user);
    }
}
