package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<User> getUsers() {
        return service.getAllUsers();
    }

    @GetMapping("/{email}")
    public User getUser(@PathVariable String email) {
        return service.getUserByEmail(email);
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        return service.createUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return service.updateUser(id, user);
    }

    @PatchMapping("/{id}")
    public User patchUser(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return service.patchUser(id, updates);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
    }
}
