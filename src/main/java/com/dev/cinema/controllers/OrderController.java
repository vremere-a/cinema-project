package com.dev.cinema.controllers;

import com.dev.cinema.model.User;
import com.dev.cinema.model.dto.order.OrderDtoMapper;
import com.dev.cinema.model.dto.order.OrderResponseDto;
import com.dev.cinema.service.OrderService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;
    private final OrderDtoMapper orderDtoMapper;

    @Autowired
    public OrderController(OrderService orderService,
                           ShoppingCartService shoppingCartService,
                           UserService userService,
                           OrderDtoMapper orderDtoMapper) {
        this.orderService = orderService;
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.orderDtoMapper = orderDtoMapper;
    }

    @PostMapping("/complete")
    public void completeOrder(@Valid Authentication authentication) {
        String email = ((UserDetails) authentication.getPrincipal()).getUsername();
        User user = userService.findByEmail(email).get();
        orderService.completeOrder(
                shoppingCartService.getByUser(user).getTickets(), user);
    }

    @GetMapping
    public List<OrderResponseDto> getOrderHistory(Authentication authentication) {
        String email = ((UserDetails) authentication.getPrincipal()).getUsername();
        return orderService.getOrderHistory(userService.findByEmail(email).get())
                .stream()
                .map(orderDtoMapper::mapToResponseDto)
                .collect(Collectors.toList());
    }
}
