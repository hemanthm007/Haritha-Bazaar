package com.haritha_bazaar.controller;

import com.haritha_bazaar.model.Order;
import com.haritha_bazaar.service.OrderService;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")

public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @Autowired
private OrderService orderService;

    @GetMapping("/orders/{email}")
    public List<Order> getOrders(@PathVariable String email, @AuthenticationPrincipal Jwt jwt) {
        String loggedInEmail = jwt.getClaim("email");
        if (!loggedInEmail.equals(email)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not authorized");
        }
        return orderService.getOrdersByEmail(email);
    }

    @PostMapping
    public Order placeOrder(@RequestBody Order order) {
        return service.placeOrder(order);
    }
}
