package com.francadev.agregadordeinvestimentos.controllers;

import com.francadev.agregadordeinvestimentos.dto.CreateUserDto;
import com.francadev.agregadordeinvestimentos.models.User;
import com.francadev.agregadordeinvestimentos.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String indexUsers(){
        return "Index usuarios";
    }
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody CreateUserDto createUserDto){
        var userId = userService.createUser(createUserDto);
        return ResponseEntity.created(URI.create("/api/v1/users/user/"+userId.toString())).build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") String userId){
        return null;
    }



}
