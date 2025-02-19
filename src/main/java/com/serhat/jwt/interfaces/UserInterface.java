package com.serhat.jwt.interfaces;

import com.serhat.jwt.entity.AppUser;


public interface UserInterface {
    AppUser findUserByUsername(String username);
}
