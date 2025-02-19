package com.serhat.jwt.interfaces;

import com.serhat.jwt.entity.AppUser;
import com.serhat.jwt.entity.enums.Role;
import com.serhat.jwt.jwt.JwtUtil;
import com.serhat.jwt.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class TokenInterfaceImpl implements TokenInterface{


    protected final JwtUtil jwtUtil;
    protected final UserRepository userRepository;

    @Override
    public String extractTokenFromRequest(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }

        return Optional.ofNullable(request.getCookies())
                .flatMap(cookies -> Arrays.stream(cookies)
                        .filter(cookie -> "jwt".equals(cookie.getName()))
                        .findFirst()
                        .map(Cookie::getValue))
                .orElse(null);
    }
    @Override
    public AppUser getUserFromToken(HttpServletRequest request) {
        String token = extractTokenFromRequest(request);
        if (token == null) {
            throw new RuntimeException("Token not found in request");
        }

        String username = jwtUtil.extractUsername(token);
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
    }

    @Override
    public void validateRole(HttpServletRequest request, Role... allowedRoles) {
        String token = extractTokenFromRequest(request);
        Role userRole = jwtUtil.extractRole(token);
        if (Stream.of(allowedRoles).noneMatch(role -> role == userRole)) {
            throw new RuntimeException("Unauthorized action. Required role: " + List.of(allowedRoles));
        }
    }
}
