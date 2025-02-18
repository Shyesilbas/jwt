package com.serhat.jwt.service;

import com.serhat.jwt.entity.User;
import com.serhat.jwt.interfaces.UserInterface;
import com.serhat.jwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserInterface{
    private final UserRepository userRepository;

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(()-> new RuntimeException("User not found by username : "+username));
    }
}
