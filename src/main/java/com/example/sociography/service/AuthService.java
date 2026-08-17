package com.example.sociography.service;

import com.example.sociography.model.Partner;
import com.example.sociography.model.Photographer;
import com.example.sociography.repository.PartnerRepository;
import com.example.sociography.repository.PhotographerRepository;
import com.example.sociography.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private PartnerRepository partnerRepository;

    @Autowired
    private PhotographerRepository photographerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

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
        return jwtUtil.generateToken(id, email, role);
    }
}
