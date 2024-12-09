package com.example.sociography.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sociography.model.Like;
import com.example.sociography.model.LikeId;

public interface LikeRepository extends JpaRepository<Like, LikeId> {
    List<Like> findByPictureId(int pictureId);
    long countByPictureId(int pictureId);
    Optional<Like> findByPictureIdAndPhotographerId(int pictureId, int photographerId);
}
