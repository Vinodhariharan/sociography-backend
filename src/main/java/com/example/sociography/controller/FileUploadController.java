package com.example.sociography.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.example.sociography.model.Photographer;
import com.example.sociography.model.Picture;
import com.example.sociography.repository.PhotographerRepository;
import com.example.sociography.service.PictureService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Arrays;

@RestController
@RequestMapping("/api")
public class FileUploadController {

    @Autowired
    private PhotographerRepository photographerRepository;

    @Autowired
    private PictureService pictureService;

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestPart("file") MultipartFile file,
                                                   @RequestParam("location") String location,
                                                   @RequestParam("description") String description,
                                                   @RequestParam("categories") String[] categories,
                                                   @RequestParam("email") String email) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is empty");
        }

        Photographer photographer = photographerRepository.findByEmail(email);
        if (photographer == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Photographer not found");
        }

        try {
            Picture picture = new Picture();
            picture.setLocation(location);
            picture.setDescription(description);
            picture.setPhotographer(photographer);
            picture.setPicture(file.getBytes());

            pictureService.save(picture, Arrays.asList(categories));

            return ResponseEntity.status(HttpStatus.OK).body("File uploaded successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing file");
        }
    }
}
