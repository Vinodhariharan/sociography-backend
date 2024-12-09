package com.example.sociography.controller;

import com.example.sociography.model.Picture;
import com.example.sociography.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pictures")
public class PictureController {

    @Autowired
    private PictureService pictureService;

    @GetMapping
    public List<Picture> getAllPictures() {
        return pictureService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Picture> getPictureById(@PathVariable Integer id) {
        Optional<Picture> picture = pictureService.findById(id);
        return picture.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Picture createPicture(@RequestBody Picture picture, @RequestParam List<String> categories) {
        return pictureService.save(picture, categories);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Picture> updatePicture(@PathVariable Integer id, @RequestBody Picture updatedPicture, @RequestParam List<String> categories) {
        return pictureService.findById(id)
            .map(existingPicture -> {
                updatedPicture.setId(existingPicture.getId());
                return ResponseEntity.ok(pictureService.save(updatedPicture, categories));
            }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePicture(@PathVariable Integer id) {
        return pictureService.findById(id)
            .map(existingPicture -> {
                pictureService.deleteById(id);
                return ResponseEntity.noContent().build();
            }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/page")
    public Page<Picture> getPaginatedPictures(@RequestParam int page, @RequestParam int size) {
        return pictureService.findPaginated(page, size);
    }
    
    @GetMapping("/search")
    public List<Picture> searchPictures(@RequestParam String keyword) {
        return pictureService.searchPicturesByDescription(keyword);
    }
    
    
}
