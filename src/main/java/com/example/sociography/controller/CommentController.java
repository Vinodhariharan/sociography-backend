package com.example.sociography.controller;

import com.example.sociography.model.Comment;
import com.example.sociography.model.Photographer;
import com.example.sociography.model.Picture;
import com.example.sociography.repository.CommentRepository;
import com.example.sociography.repository.PhotographerRepository;
import com.example.sociography.repository.PictureRepository;
import com.example.sociography.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pictures/{pictureId}/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;
    
    @Autowired
    private PictureRepository pictureRepository;

    @Autowired
    private PhotographerRepository photographerRepository;
    
    @Autowired
    private CommentRepository commentRepository;

    @GetMapping
    public List<Comment> getCommentsByPictureId(@PathVariable int pictureId) {
        return commentService.getCommentsByPictureId(pictureId);
    }
    
//    @PostMapping
//    public Comment addComment(@PathVariable int pictureId, @RequestBody Comment comment) {
//        Picture picture = pictureRepository.findById(pictureId).orElseThrow(() -> new RuntimeException("Picture not found"));
//        Photographer photographer = photographerRepository.findById(picture.getPhotographer().getId())
//                .orElseThrow(() -> new RuntimeException("Photographer not found"));
//
//        comment.setPicture(picture);
//        comment.setPhotographer(photographer);
//        comment.setTimestamp(LocalDateTime.now());
//
//        return commentRepository.save(comment);
//    }

    @PostMapping
    public Comment addComment(@PathVariable int pictureId, @RequestBody Comment comment) {
        Picture picture = pictureRepository.findById(pictureId)
            .orElseThrow(() -> new RuntimeException("Picture not found"));

        Photographer photographer = photographerRepository.findById(comment.getPhotographer().getId())
            .orElseThrow(() -> new RuntimeException("Photographer not found"));

        comment.setPicture(picture);
        comment.setPhotographer(photographer);
        comment.setTimestamp(LocalDateTime.now());

        return commentRepository.save(comment);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable int commentId) {
        commentRepository.deleteById(commentId);
    }
}

