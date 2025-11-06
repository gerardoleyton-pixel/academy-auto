package com.gerardoleyton.controller;

import com.gerardoleyton.model.User;
import com.gerardoleyton.security.JwtUtil;
import com.gerardoleyton.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authManager;

    public AuthController(UserService userService, JwtUtil jwtUtil, AuthenticationManager authManager) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.authManager = authManager;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");
        String email = body.get("email");
        String role = body.getOrDefault("role", "ROLE_USER");
        User u = new User(username, password, role, email);
        User saved = userService.register(u);
        return ResponseEntity.ok(Map.of("id", saved.getId(), "username", saved.getUsername()));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        try {
            String username = body.get("username");
            String password = body.get("password");
            authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            User user = userService.findByUsername(username).orElseThrow();
            String token = jwtUtil.generateToken(username, user.getRole());
            return ResponseEntity.ok(Map.of("token", token, "role", user.getRole()));
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(401).body(Map.of("error", "Invalid credentials"));
        }
    }
}
