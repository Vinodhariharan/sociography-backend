package com.example.sociography.controller;

import com.example.sociography.dto.PhotographerProfileDTO;
import com.example.sociography.dto.PhotographerProfileResponse;
import com.example.sociography.model.Photographer;
import com.example.sociography.model.Picture;
import com.example.sociography.service.PhotographerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/photographers")
public class PhotographerController {

    @Autowired
    private PhotographerService photographerService;

    @GetMapping
    public List<Photographer> getAllPhotographers() {
        return photographerService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Photographer> getPhotographerById(@PathVariable Integer id) {
        Optional<Photographer> photographer = photographerService.findById(id);
        return photographer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PutMapping("/email/{email}/edit")
    public ResponseEntity<Photographer> updatePhotographerProfile(@PathVariable String email, @RequestBody Photographer updatedPhotographer) {
        Photographer photographer = photographerService.getPhotographerByEmail(email);
        if (photographer != null) {
            // Update fields
        	System.out.println(photographer.getName());
        	System.out.println(updatedPhotographer.getName());

            photographer.setName(updatedPhotographer.getName());
            photographer.setSelfInfo(updatedPhotographer.getSelfInfo());
            photographer.setContact(updatedPhotographer.getContact());
            photographer.setEmail(updatedPhotographer.getEmail());

            // Update profile picture if provided
            if (updatedPhotographer.getProfilePic() != null) {
                photographer.setProfilePic(updatedPhotographer.getProfilePic());
            }

            // Save updated photographer
            Photographer savedPhotographer = photographerService.save(photographer);
        	System.out.println(photographer);

            return ResponseEntity.ok(savedPhotographer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @PostMapping
    public Photographer createPhotographer(@RequestBody Photographer photographer) {
        return photographerService.save(photographer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Photographer> updatePhotographer(@PathVariable Integer id, @RequestBody Photographer updatedPhotographer) {
        return photographerService.findById(id)
            .map(existingPhotographer -> {
                updatedPhotographer.setId(existingPhotographer.getId());
                return ResponseEntity.ok(photographerService.save(updatedPhotographer));
            }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePhotographer(@PathVariable Integer id) {
        return photographerService.findById(id)
            .map(existingPhotographer -> {
                photographerService.deleteById(id);
                return ResponseEntity.noContent().build();
            }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // For profile page
    @GetMapping("/email/{email}")
    public ResponseEntity<PhotographerProfileResponse> getPhotographerByEmail(@PathVariable String email) {
        Photographer photographer = photographerService.getPhotographerByEmail(email);
        int followersCount = photographerService.getFollowersCountByPhotographerId(photographer.getId());
        int followingCount = photographerService.getFollowingCountByPhotographerId(photographer.getId());

        PhotographerProfileResponse response = new PhotographerProfileResponse(photographer, followersCount, followingCount);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{email}/pictures")
    public List<Picture> getPictures(@PathVariable String email) {
        Photographer photographer = photographerService.getPhotographerByEmail(email);
        return photographerService.getPicturesByPhotographerId(photographer.getId());
    }
    
    @GetMapping("/{photographerId}/profile")
    public ResponseEntity<PhotographerProfileDTO> getPhotographerProfile(@PathVariable int photographerId) {
        Optional<Photographer> photographerOpt = photographerService.findById(photographerId);

        if (photographerOpt.isPresent()) {
            Photographer photographer = photographerOpt.get();
            int followersCount = photographerService.getFollowersCountByPhotographerId(photographerId);
            int followingCount = photographerService.getFollowingCountByPhotographerId(photographerId);
            List<Picture> pictures = photographerService.getPicturesByPhotographerId(photographerId);

            PhotographerProfileDTO profileDTO = new PhotographerProfileDTO(
                photographer.getName(),
                photographer.getSelfInfo(),
                photographer.getProfilePic(),
                followersCount,
                followingCount,
                pictures
            );

            return ResponseEntity.ok(profileDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint for fetching pictures by photographer ID
    @GetMapping("/pictures/{photographerId}")
    public ResponseEntity<List<Picture>> getPicturesByPhotographerId(@PathVariable int photographerId) {
        List<Picture> pictures = photographerService.getPicturesByPhotographerId(photographerId);
        if (pictures.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(pictures);
        }
    }
}
