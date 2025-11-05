package com.academy.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academy.model.User;
import com.academy.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;
    public AuthController(UserService userService) { this.userService = userService; }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User u) {
        // Basic: ensure username not taken
        if (userService.findByUsername(u.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        // In production, hash passwords
        if (u.getRole() == null || u.getRole().isEmpty()) u.setRole("STUDENT");
        User saved = userService.save(u);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User u) {
        return userService.findByUsername(u.getUsername())
                .filter(found -> found.getPassword().equals(u.getPassword()))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(401).build());
    }
}
