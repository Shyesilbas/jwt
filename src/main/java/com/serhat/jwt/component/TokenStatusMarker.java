package com.serhat.jwt.component;

import com.serhat.jwt.entity.Token;
import com.serhat.jwt.entity.enums.TokenStatus;
import com.serhat.jwt.jwt.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class TokenStatusMarker implements CommandLineRunner {
    private final TokenService tokenService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Starting token status check...");

        tokenService.getAllTokens().forEach(this::checkTokenStatus);

        System.out.println("Token status check completed.");
    }

    private void checkTokenStatus(Token token) {
        if (token.getExpiresAt().before(new Date()) && token.getTokenStatus()==TokenStatus.ACTIVE) {
            token.setTokenStatus(TokenStatus.EXPIRED);
            token.setExpired_at(token.getExpiresAt());
            tokenService.saveToken(token);
            System.out.println("Token expired and status updated for user: " + token.getUsername());

        }
    }
}
