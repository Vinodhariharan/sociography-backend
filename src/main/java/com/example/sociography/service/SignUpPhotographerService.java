package com.example.sociography.service;

import com.example.sociography.model.Photographer;
import com.example.sociography.repository.SignUpPhotographerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignUpPhotographerService {

	@Autowired
    private SignUpPhotographerRepo signUpPhotographerRepo;
    private BCryptPasswordEncoder passwordEncoder;

//    public SignUpPhotographerService(SignUpPhotographerRepo signUpPhotographerRepo, BCryptPasswordEncoder passwordEncoder) {
//        this.signUpPhotographerRepo = signUpPhotographerRepo;
//        this.passwordEncoder = passwordEncoder;
//    }

    public Photographer signUpPhotographer(Photographer photographer) throws Exception {
        // Check if username or email already exists
        if (signUpPhotographerRepo.existsByUsername(photographer.getUsername())) {
            throw new Exception("Username is already taken.");
        }

        if (signUpPhotographerRepo.existsByEmail(photographer.getEmail())) {
            throw new Exception("Email is already registered.");
        }

        // Encrypt the password before saving
        photographer.setPassword(passwordEncoder.encode(photographer.getPassword()));

        // Save the photographer to the database
        return signUpPhotographerRepo.save(photographer);
    }
}
