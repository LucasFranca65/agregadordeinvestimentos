package com.francadev.agregadordeinvestimentos.controllers;

import com.francadev.agregadordeinvestimentos.dto.CreateUserDto;
import com.francadev.agregadordeinvestimentos.dto.UpdateUserDto;
import com.francadev.agregadordeinvestimentos.models.User;
import com.francadev.agregadordeinvestimentos.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> indexUsers(){
        List<User> usersList = userService.getAllUsers();
        if(usersList.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(usersList);
        }
    }
    @PostMapping
    public ResponseEntity<User> saveNewUser(@RequestBody CreateUserDto createUserDto){
        var userId = userService.createUser(createUserDto);
        if(userId == null){
            System.out.println("Erro ao salvar");
            return ResponseEntity.badRequest().build();
        }else{
            return ResponseEntity.created(URI.create("/api/v1/users/user/"+userId.toString())).build();
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") String userId){
        var user = userService.getUserById(userId);
        if(user.isPresent()){
            return ResponseEntity.ok(user.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable("userId") String userId){
        if(userService.deleteUserById(userId)){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUserById(@PathVariable("userId") String userId, @RequestBody UpdateUserDto updateUserDto){
        if(userService.updateUserById(userId,updateUserDto)){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }



}
