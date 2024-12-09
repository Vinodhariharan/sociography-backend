package com.example.sociography.controller;

import com.example.sociography.model.Photographer;
import com.example.sociography.repository.SignUpPhotographerRepo;
import com.example.sociography.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/signup")
@CrossOrigin(origins = "http://localhost:3000/")
public class SignUpPhotographerController {

    @Autowired
    private SignUpPhotographerRepo signUpPhotographerRepo;

    @Autowired
    private AuthService authService;

    @PostMapping("/photographer")
    public ResponseEntity<String> signUpPhotographer(
            @RequestParam("name") String name,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("dob") LocalDate dob,
            @RequestParam("selfInfo") String selfInfo,
            @RequestParam("location") String location,
            @RequestParam("contactNo") String contactNo,
            @RequestParam("email") String email,
            @RequestParam(value = "profilePic", required = false) MultipartFile profilePic) {

        if (signUpPhotographerRepo.existsByUsername(username)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
        }

        if (signUpPhotographerRepo.existsByEmail(email)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
        }

        Photographer photographer = new Photographer();
        photographer.setName(name);
        photographer.setUsername(username);
        photographer.setPassword(password);
        photographer.setDob(dob);
        photographer.setSelfInfo(selfInfo);
        photographer.setLocation(location);
        photographer.setContact(contactNo);
        photographer.setEmail(email);

        try {
            if (profilePic != null && !profilePic.isEmpty()) {
                photographer.setProfilePic(profilePic.getBytes());
            }
            signUpPhotographerRepo.save(photographer);

            // Generate token for the newly registered photographer
            String token = authService.generateToken(photographer.getId(), email, "photographer");
            return ResponseEntity.status(HttpStatus.CREATED).body("Photographer registered successfully. Token: " + token);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing profile picture");
        }
    }
}
