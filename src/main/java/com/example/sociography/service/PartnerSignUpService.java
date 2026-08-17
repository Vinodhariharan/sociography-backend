package com.example.sociography.service;

import com.example.sociography.model.Partner;
import com.example.sociography.repository.PartnerSignUpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PartnerSignUpService {

    private final PartnerSignUpRepo partnerSignUpRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PartnerSignUpService(PartnerSignUpRepo partnerSignUpRepo, PasswordEncoder passwordEncoder) {
        this.partnerSignUpRepo = partnerSignUpRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public Partner signUpPartner(Partner partner) throws Exception {
        // Check if username or email already exists
        if (partnerSignUpRepo.existsByUsername(partner.getUsername())) {
            throw new Exception("Username is already taken.");
        }

        if (partnerSignUpRepo.existsByEmail(partner.getEmail())) {
            throw new Exception("Email is already registered.");
        }

        // Encrypt the password before saving
        partner.setPassword(passwordEncoder.encode(partner.getPassword()));

        // Save the partner to the database
        return partnerSignUpRepo.save(partner);
    }
}
