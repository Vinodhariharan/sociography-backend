package com.example.sociography.service;

import com.example.sociography.model.Partner;
import com.example.sociography.model.Photographer;
import com.example.sociography.repository.PartnerRepository;
import com.example.sociography.repository.PhotographerRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthService {

    private static final String SECRET_KEY = "your_secret_key";

    @Autowired
    private PartnerRepository partnerRepository;

    @Autowired
    private PhotographerRepository photographerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String authenticate(String email, String password) {
        Photographer photographer = photographerRepository.findByEmail(email);
        if (photographer != null && passwordEncoder.matches(password, photographer.getPassword())) {
            return generateToken(photographer.getId(), email, "photographer");
        }

        Partner partner = partnerRepository.findByEmail(email);
        if (partner != null && passwordEncoder.matches(password, partner.getPassword())) {
            return generateToken(partner.getId(), email, "partner");
        }

        return null;
    }

    public String generateToken(int id, String email, String role) {
        System.out.println("Generating token for user: " + email + " with role: " + role);
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .claim("id", id) // Add the id claim
                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day expiration
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }
}
