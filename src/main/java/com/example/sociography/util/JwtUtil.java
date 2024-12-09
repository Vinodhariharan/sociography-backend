// src/main/java/com/example/sociography/util/JwtUtil.java
package com.example.sociography.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtUtil {
    private static final String SECRET_KEY = "your_secret_key";

    public static Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    public static String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    public static String extractRole(String token) {
        return extractClaims(token).get("role", String.class);
    }
}
