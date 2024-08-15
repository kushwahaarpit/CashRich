package com.cashRich.assignment;

import com.cashRich.assignment.entity.User;
import com.cashRich.assignment.repository.UserRepository;
import com.cashRich.assignment.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    public void testSignup() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("Test@123");

        when(passwordEncoder.encode("Test@123")).thenReturn("encodedPassword");
        when(userRepository.save(user)).thenReturn(user);

        User createdUser = userService.signup(user);

        assertTrue(createdUser.getPassword().equals("encodedPassword"));
    }
}
