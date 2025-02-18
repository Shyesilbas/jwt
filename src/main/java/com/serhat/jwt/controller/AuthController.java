package com.serhat.jwt.controller;

import com.serhat.jwt.dto.requests.LoginRequest;
import com.serhat.jwt.dto.requests.RegisterRequest;
import com.serhat.jwt.dto.responses.AuthResponse;
import com.serhat.jwt.dto.responses.RegisterResponse;
import com.serhat.jwt.service.auth.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request){
        return ResponseEntity.ok(authService.register(request));
    }
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.login(request));
    }
    @PostMapping("/logout")
    public ResponseEntity<AuthResponse> logout(HttpServletRequest servletRequest){
        return ResponseEntity.ok(authService.logout(servletRequest));
    }
}
