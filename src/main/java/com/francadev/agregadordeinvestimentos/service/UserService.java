package com.francadev.agregadordeinvestimentos.service;

import com.francadev.agregadordeinvestimentos.dto.CreateUserDto;
import com.francadev.agregadordeinvestimentos.dto.UpdateUserDto;
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

    public boolean deleteUserById(String userId) {
        var userDeleted = userRepository.findById(UUID.fromString(userId));
        if(userDeleted.isPresent()){
            userRepository.deleteById(userDeleted.get().getUser_id());
            return true;
        }else{
            return false;
        }
    }

    public boolean updateUserById(String userId, UpdateUserDto updateUserDto){
        var userEdit = userRepository.findById(UUID.fromString(userId));
        if(userEdit.isPresent()){
            var userUpdate = userEdit.get();
            userUpdate.setUser_nome(updateUserDto.user_name());
            userUpdate.setUser_password(updateUserDto.user_password());
            userRepository.save(userUpdate);
            return true;
        }else{
            return false;
        }
    }

}
