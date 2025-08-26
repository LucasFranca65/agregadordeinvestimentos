package com.francadev.agregadordeinvestimentos.service;

import com.francadev.agregadordeinvestimentos.dto.CreateUserDto;
import com.francadev.agregadordeinvestimentos.models.User;
import com.francadev.agregadordeinvestimentos.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.Instant;
import java.util.UUID;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UUID createUser(@RequestBody CreateUserDto createUserDto){

        try {
            var savedUser = userRepository.save(new User(UUID.randomUUID(),
                    createUserDto.user_nome(),
                    createUserDto.user_email(),
                    createUserDto.user_password(), Instant.now(),null)
            );

            return savedUser.getUser_id();


        }catch (Exception e){
            return null;
        }



    }
}
