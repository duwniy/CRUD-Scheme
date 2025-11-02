package com.example.demo.controller;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    // ✅ Получить всех пользователей (возвращаем DTO)
    @GetMapping
    public List<UserDTO> getUsers() {
        return service.getAllUsers().stream()
                .map(user -> new UserDTO(user.getName(), user.getEmail()))
                .collect(Collectors.toList());
    }

    // ✅ Получить пользователя по email (возвращаем DTO)
    @GetMapping("/{email}")
    public UserDTO getUser(@PathVariable String email) {
        User user = service.getUserByEmail(email);
        return new UserDTO(user.getName(), user.getEmail());
    }

    // ✅ Добавить пользователя (принимаем DTO, создаём сущность)
    @PostMapping
    public UserDTO addUser(@RequestBody UserDTO userDTO) {
        User created = service.createUser(new User(userDTO.getName(), userDTO.getEmail()));
        return new UserDTO(created.getName(), created.getEmail());
    }

    // ❌ Удаление не требует DTO
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
    }

    // ✅ Полное обновление (PUT)
    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        User updated = new User(userDTO.getName(), userDTO.getEmail());
        User result = service.updateUser(id, updated);
        return new UserDTO(result.getName(), result.getEmail());
    }

    // ✅ Частичное обновление (PATCH)
    @PatchMapping("/{id}")
    public UserDTO patchUser(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        User patched = service.patchUser(id, updates);
        return new UserDTO(patched.getName(), patched.getEmail());
    }

    // HTML страница
    @GetMapping("/button")
    public String button() {
        return "redirect:/button.html";
    }
}
