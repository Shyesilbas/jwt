package com.serhat.jwt.interfaces;

import com.serhat.jwt.entity.User;
import com.serhat.jwt.entity.enums.Role;
import jakarta.servlet.http.HttpServletRequest;

public interface TokenInterface {
    String extractTokenFromRequest(HttpServletRequest request);
    User getUserFromToken(HttpServletRequest request);

    void validateRole(HttpServletRequest request, Role... allowedRoles);
}
