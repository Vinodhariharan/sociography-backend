package com.example.sociography.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sociography.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByPictureId(int pictureId);
}
