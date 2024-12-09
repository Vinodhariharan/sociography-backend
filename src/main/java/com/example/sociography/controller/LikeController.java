package com.example.sociography.controller;

import com.example.sociography.dto.LikeRequest;
import com.example.sociography.model.Like;
import com.example.sociography.model.LikeId;
import com.example.sociography.model.Picture;
import com.example.sociography.model.Photographer;
import com.example.sociography.repository.PictureRepository;
import com.example.sociography.repository.LikeRepository;
import com.example.sociography.repository.PhotographerRepository;
import com.example.sociography.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/pictures/{pictureId}/likes")
public class LikeController {

    @Autowired
    private LikeService likeService;

    @Autowired
    private PictureRepository pictureRepository;

    @Autowired
    private PhotographerRepository photographerRepository;
    
    @Autowired
    private LikeRepository likeRepository;

    @GetMapping
    public List<Like> getLikes(@PathVariable int pictureId) {
        return likeService.findAll().stream()
                .filter(like -> like.getPicture().getId() == pictureId)
                .toList();
    }

    @GetMapping("/count")
    public long getLikeCount(@PathVariable int pictureId) {
        return likeService.findAll().stream()
                .filter(like -> like.getPicture().getId() == pictureId)
                .count();
    }

    @PostMapping
    public Like addLike(@RequestBody LikeRequest likeRequest) {
        int pictureId = likeRequest.getPictureId();
        int photographerId = likeRequest.getPhotographerId();
        System.out.print(pictureId);
        Picture picture = pictureRepository.findById(pictureId)
                .orElseThrow(() -> new RuntimeException("Picture not found"));
        Photographer photographer = photographerRepository.findById(photographerId)
                .orElseThrow(() -> new RuntimeException("Photographer not found"));

        LikeId likeId = new LikeId(pictureId, photographerId);
        Like like = new Like();
        like.setId(likeId);
        like.setPicture(picture);
        like.setPhotographer(photographer);
        like.setTimestamp(LocalDateTime.now());
        
        return likeRepository.save(like);
    }

    @DeleteMapping
    public void removeLike(@RequestBody LikeRequest likeRequest) {
        LikeId likeId = new LikeId(likeRequest.getPictureId(), likeRequest.getPhotographerId());
        Like like = likeService.findById(likeId)
                .orElseThrow(() -> new RuntimeException("Like not found"));

        likeService.deleteById(likeId);
    }

    @PostMapping("/check")
    public boolean checkIfLiked(@RequestBody LikeRequest likeRequest) {
        return likeService.findById(new LikeId(likeRequest.getPictureId(), likeRequest.getPhotographerId())).isPresent();
    }
}
