package com.example.sociography.util;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.access.AccessDeniedException;

/**
 * Reads the identity JwtRequestFilter already verified and attached to the
 * request, so controllers can bind writes to the caller instead of trusting
 * an id supplied in the request body/params.
 */
public class AuthenticatedUser {

    public static int getId(HttpServletRequest request) {
        Claims claims = (Claims) request.getAttribute("claims");
        if (claims == null) {
            throw new AccessDeniedException("Not authenticated");
        }
        return claims.get("id", Integer.class);
    }

    private AuthenticatedUser() {
    }
}
