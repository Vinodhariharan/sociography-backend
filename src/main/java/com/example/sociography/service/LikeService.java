package com.example.sociography.service;

import com.example.sociography.model.Like;
import com.example.sociography.model.LikeId;
import com.example.sociography.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeService {

    @Autowired
    private LikeRepository likeRepository;

    public List<Like> findAll() {
        return likeRepository.findAll();
    }

    public Optional<Like> findById(LikeId id) {
        return likeRepository.findById(id);
    }

    public Like save(Like like) {
        return likeRepository.save(like);
    }

    public void deleteById(LikeId id) {
        likeRepository.deleteById(id);
    }
}
