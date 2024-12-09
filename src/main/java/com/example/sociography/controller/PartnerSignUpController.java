package com.example.sociography.controller;

import com.example.sociography.model.Partner;
import com.example.sociography.service.PartnerSignUpService;
import com.example.sociography.service.AuthService; // Import AuthService
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/partners")
@CrossOrigin(origins = "http://localhost:3000/")
public class PartnerSignUpController {

    @Autowired
    private PartnerSignUpService partnerSignUpService;

    @Autowired
    private AuthService authService; // Add AuthService

    @PostMapping("/signup")
    public ResponseEntity<String> signUpPartner(@RequestBody Partner partner) {
        try {
            partnerSignUpService.signUpPartner(partner);
            // Generate a token after successful signup
            String token = authService.generateToken(partner.getId(),partner.getEmail(),"partner"); // Modify AuthService to add this method
            return ResponseEntity.status(HttpStatus.CREATED).body("Partner signed up successfully! Token: " + token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
