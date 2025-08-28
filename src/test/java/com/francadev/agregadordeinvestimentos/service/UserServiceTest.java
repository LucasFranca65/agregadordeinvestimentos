package com.francadev.agregadordeinvestimentos.service;

import com.francadev.agregadordeinvestimentos.dto.CreateUserDto;
import com.francadev.agregadordeinvestimentos.models.User;
import com.francadev.agregadordeinvestimentos.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    @Nested
    class creatUser{

        @Test
        @DisplayName("Should create a user with success ! ")
        void shouldCreateUserWithSuccess(){
            //
            var user = new User(
                    UUID.randomUUID(),
                    "userName",
                    "userEmail",
                    "123",
                    Instant.now(),
                    null
            );
            doReturn(user).when(userRepository).save(any());
            var input = new CreateUserDto("userName","userEmail@email.com","123");
            //Act
            var output = userService.createUser(input);
            //Assert
            assertNotNull(output);
        }

    }

}