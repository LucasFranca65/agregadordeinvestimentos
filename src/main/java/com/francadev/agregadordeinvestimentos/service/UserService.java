package com.francadev.agregadordeinvestimentos.service;

import com.francadev.agregadordeinvestimentos.dto.CreateUserDto;
import com.francadev.agregadordeinvestimentos.models.User;
import com.francadev.agregadordeinvestimentos.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public UUID createUser(@RequestBody CreateUserDto createUserDto){

            try {
                var entity = new User(
                        null,
                        createUserDto.user_name(),
                        createUserDto.user_email(),
                        createUserDto.user_password(),
                        Instant.now(),
                        null
                );
                var userSaved = userRepository.save(entity);

                return userSaved.getUser_id();
            }catch (Exception e){
                return null;
            }




    }

    public Optional<User> getUserById(String userId) {
        return userRepository.findById(UUID.fromString(userId));

    }
}
