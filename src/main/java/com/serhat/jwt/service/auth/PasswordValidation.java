package com.serhat.jwt.service.auth;

import com.serhat.jwt.exception.InvalidCredentialsException;
import com.serhat.jwt.interfaces.PasswordValidationInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PasswordValidation implements PasswordValidationInterface {
    private final PasswordEncoder passwordEncoder;
    @Override
    public void validatePassword(String rawPassword, String encodedPassword) {
        if (!passwordEncoder.matches(rawPassword, encodedPassword)) {
            log.warn("Invalid password attempt");
            throw new InvalidCredentialsException("Invalid credentials");
        }
    }
}
