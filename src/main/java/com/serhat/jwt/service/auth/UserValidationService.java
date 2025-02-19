package com.serhat.jwt.service.auth;

import com.serhat.jwt.dto.requests.RegisterRequest;
import com.serhat.jwt.entity.AppUser;
import com.serhat.jwt.exception.EmailExistException;
import com.serhat.jwt.exception.PhoneExistsException;
import com.serhat.jwt.exception.UsernameExists;
import com.serhat.jwt.interfaces.UserValidationInterface;
import com.serhat.jwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserValidationService implements UserValidationInterface {
    private final UserRepository userRepository;
    @Override
    public void validateUserRegistration(RegisterRequest request) {
        Optional<AppUser> existingUser = userRepository.findByEmailOrUsernameOrPhone(
                request.email(),
                request.username(),
                request.phone()
        );

        existingUser.ifPresent(user -> {
            if (user.getEmail().equals(request.email())) {
                throw new EmailExistException("Email already exists!");
            }
            if (user.getUsername().equals(request.username())) {
                throw new UsernameExists("Username already exists!");
            }
            if (user.getPhone().equals(request.phone())) {
                throw new PhoneExistsException("Phone number already exists!");
            }
        });
    }
}
