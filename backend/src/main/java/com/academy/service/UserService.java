package com.academy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.academy.model.User;
import com.academy.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository repo;
    public UserService(UserRepository repo) { this.repo = repo; }

    public List<User> findAll() { return repo.findAll(); }
    public Optional<User> findById(Long id) { return repo.findById(id); }
    public Optional<User> findByUsername(String username) { return repo.findByUsername(username); }
    public User save(User u) { return repo.save(u); }
    public void deleteById(Long id) { repo.deleteById(id); }
}
