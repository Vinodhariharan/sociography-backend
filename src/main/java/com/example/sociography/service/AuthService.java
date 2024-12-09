package com.example.sociography.service;

import com.example.sociography.model.Partner;
import com.example.sociography.model.Photographer;
import com.example.sociography.repository.PartnerRepository;
import com.example.sociography.repository.PhotographerRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthService {

    private static final String SECRET_KEY = "your_secret_key";

    @Autowired
    private PartnerRepository partnerRepository;

    @Autowired
    private PhotographerRepository photographerRepository;

    public String authenticate(String email, String password) {
        System.out.println("Authenticating user: " + email);

        Photographer photographer = photographerRepository.findByEmail(email);
        System.out.println("Photographer found: " + (photographer != null));
        if (photographer != null && photographer.getPassword().equals(password)) {
            System.out.println("Photographer authenticated successfully");
            return generateToken(photographer.getId(), email, "photographer");
        }

        Partner partner = partnerRepository.findByEmail(email);
        System.out.println("Partner found: " + (partner != null));
        if (partner != null && partner.getPassword().equals(password)) {
            System.out.println("Partner authenticated successfully");
            return generateToken(partner.getId(), email, "partner");
        }

        System.out.println("Authentication failed for user: " + email);
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
