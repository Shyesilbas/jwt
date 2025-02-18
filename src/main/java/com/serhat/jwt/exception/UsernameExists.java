package com.serhat.jwt.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UsernameExists extends RuntimeException {
    public UsernameExists(String s) {
        super(s);
    }
}
