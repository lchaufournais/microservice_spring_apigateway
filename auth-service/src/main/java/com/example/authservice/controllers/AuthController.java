package com.example.authservice.controllers;

import com.example.authservice.dto.LoginRequest;
import com.example.authservice.entities.UsersEntity;
import com.example.authservice.services.UsersService;
import com.example.authservice.utils.JwtUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;
    private final UsersService usersService;

    public AuthController(JwtUtil jwtUtil, UsersService usersService) {
        this.jwtUtil = jwtUtil;
        this.usersService = usersService;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        String token = jwtUtil.generateToken(loginRequest.getUsername());
        UsersEntity user = new UsersEntity();
        user.setToken(token);
        usersService.addAuthtoken(user);

        return "Bearer " + token;
    }

    @GetMapping("/profile")
    public String getProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "Hello, " + authentication.getName();
    }

    @PostMapping("/validate")
    public boolean validateToken(@RequestBody String token) {
        UsersEntity user = usersService.getTokenById(token);
        if (user != null) {
            String username = jwtUtil.extractUsername(token);
            return jwtUtil.validateToken(token, username);
        }
        return false;
    }
}