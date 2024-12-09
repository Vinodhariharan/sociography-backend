package com.example.sociography.service;

import com.example.sociography.model.Partner;
import com.example.sociography.repository.PartnerSignUpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartnerSignUpService {

    private final PartnerSignUpRepo partnerSignUpRepo;

    @Autowired
    public PartnerSignUpService(PartnerSignUpRepo partnerSignUpRepo) {
        this.partnerSignUpRepo = partnerSignUpRepo;
    }

    public Partner signUpPartner(Partner partner) throws Exception {
        // Check if username or email already exists
        if (partnerSignUpRepo.existsByUsername(partner.getUsername())) {
            throw new Exception("Username is already taken.");
        }

        if (partnerSignUpRepo.existsByEmail(partner.getEmail())) {
            throw new Exception("Email is already registered.");
        }
        
        System.out.println(partner.getName());

        // Save the partner to the database
        return partnerSignUpRepo.save(partner);
    }
}
