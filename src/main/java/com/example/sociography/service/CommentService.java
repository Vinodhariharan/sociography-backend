package com.example.sociography.service;

import com.example.sociography.model.Comment;
import com.example.sociography.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;
//
//    public List<Comment> findAll() {
//        return commentRepository.findAll();
//    }
//
//    public Optional<Comment> findById(Integer id) {
//        return commentRepository.findById(id);
//    }
//
//    public Comment save(Comment comment) {
//        return commentRepository.save(comment);
//    }
//
//    public void deleteById(Integer id) {
//        commentRepository.deleteById(id);
//    }

    public List<Comment> getCommentsByPictureId(int pictureId) {
        return commentRepository.findByPictureId(pictureId);
    }
}
