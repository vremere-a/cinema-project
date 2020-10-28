package com.dev.cinema.controllers;

import com.dev.cinema.model.dto.cart.ShoppingCartDtoMapper;
import com.dev.cinema.model.dto.cart.ShoppingCartResponseDto;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shopping-carts")
public class ShoppingCartController {
    private final ShoppingCartDtoMapper cartDtoMapper;
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;
    private final MovieSessionService movieSessionService;

    @Autowired
    public ShoppingCartController(ShoppingCartDtoMapper cartDtoMapper,
                                  ShoppingCartService shoppingCartService,
                                  UserService userService,
                                  MovieSessionService movieSessionService) {
        this.cartDtoMapper = cartDtoMapper;
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.movieSessionService = movieSessionService;
    }

    @GetMapping("/by-user")
    public ShoppingCartResponseDto getCartByUser(@RequestParam("userId") Long id) {
        return cartDtoMapper.mapToResponseDto(
                shoppingCartService.getByUser(
                        userService.findById(id)));
    }

    @PostMapping("/movie-sessions")
    public void addMovieSession(@RequestParam Long movieSessionId,
                                @RequestParam Long userId) {
        shoppingCartService.addSession(movieSessionService.get(movieSessionId),
                userService.findById(userId));
    }
}
