package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class UserController {
    final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserEntity> registerUser(@RequestBody UserEntity user){
        return ResponseEntity.ok(userService.save(user));
    }

    @PostMapping("/token")
    public ResponseEntity<String> getToken(@RequestBody UserDto userDto){
        return ResponseEntity.ok(userService.getToken(userDto));
    }

    @GetMapping
    public ResponseEntity<List<UserEntity>> getAllUsers(){
        return ResponseEntity.ok(userService.getAll());
    }
}
