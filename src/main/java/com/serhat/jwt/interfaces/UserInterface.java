package com.serhat.jwt.interfaces;

import com.serhat.jwt.entity.User;

public interface UserInterface {
    User findUserByUsername(String username);
}
