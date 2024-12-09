package com.example.sociography.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.sociography.model.Category;
import com.example.sociography.model.Photographer;
import com.example.sociography.model.PicCat;
import com.example.sociography.model.PicCatId;
import com.example.sociography.model.Picture;
import com.example.sociography.repository.CategoryRepository;
import com.example.sociography.repository.PhotographerRepository;
import com.example.sociography.repository.PicCatRepository;
import com.example.sociography.repository.PictureRepository;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class FileUploadController {

    @Autowired
    private PhotographerRepository photographerRepository;

    @Autowired
    private PictureRepository pictureRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired 
    private PicCatRepository picCatRepository;

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestPart("file") MultipartFile file,
                                                   @RequestParam("location") String location,
                                                   @RequestParam("description") String description,
                                                   @RequestParam("categories") String[] categories,
                                                   @RequestParam("email") String email) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is empty");
        }

        try {
            // Fetch photographer based on email
            Photographer photographer = photographerRepository.findByEmail(email);
            if (photographer == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Photographer not found");
            }

            // Create and save Picture entity
            Picture picture = new Picture();
            picture.setLocation(location);
            picture.setDescription(description);
            picture.setPhotographer(photographer);
            picture.setPicture(file.getBytes());

            // Save Picture entity
            pictureRepository.save(picture);

            // Save categories to the database
            for (String category : categories) {
                Category cat = categoryRepository.findByName(category);
                if (cat == null) {
                    cat = new Category();
                    cat.setName(category);
                    categoryRepository.save(cat);
                }
                
                PicCat picCat = new PicCat();
                picCat.setId(new PicCatId(picture.getId(), cat.getId())); // Set composite key ID
                picCat.setPicture(picture);
                picCat.setCategory(cat);
                picCatRepository.save(picCat);
            }

            return ResponseEntity.status(HttpStatus.OK).body("File uploaded successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing file");
        }
    }
}
