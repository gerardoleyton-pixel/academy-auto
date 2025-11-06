package com.academy.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academy.model.AuthResponse;
import com.academy.model.User;
import com.academy.security.JwtUtil;
import com.academy.service.UserService;
import java.net.URI;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody User u) {
        // Basic: ensure username not taken
        if (userService.findByUsername(u.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        // In production, hash passwords
        if (u.getRole() == null || u.getRole().isEmpty()) u.setRole("STUDENT");
        User saved = userService.save(u);
        String token = jwtUtil.generateToken(saved.getUsername());
        return ResponseEntity.created(URI.create("/api/auth/register"))
                .body(new AuthResponse(token, saved));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody User u) {
        return userService.findByUsername(u.getUsername())
                .filter(found -> found.getPassword().equals(u.getPassword()))
                .map(user -> {
                    String token = jwtUtil.generateToken(user.getUsername());
                    return ResponseEntity.ok(new AuthResponse(token, user));
                })
                .orElse(ResponseEntity.status(401).build());
    }
}
