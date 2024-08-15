package com.cashRich.assignment.controller;


import com.cashRich.assignment.entity.User;
import com.cashRich.assignment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody User user) {
        User createdUser = userService.signup(user);
        return ResponseEntity.ok(createdUser);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestParam String username, @RequestParam String password) {
        return userService.login(username, password)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(401).build());
    }

    @PutMapping("/{userId}/update")
    public ResponseEntity<User> updateProfile(@PathVariable Long userId, @RequestBody User user) {
        User updatedUser = userService.updateProfile(userId, user);
        return ResponseEntity.ok(updatedUser);
    }
}
