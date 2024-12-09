package com.example.sociography.service;

import com.example.sociography.model.Follower;
import com.example.sociography.model.Photographer;
import com.example.sociography.repository.FollowerRepository;
import com.example.sociography.repository.PhotographerRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FollowerService {

    @Autowired
    private FollowerRepository followerRepository;

    public List<Follower> findAll() {
        return followerRepository.findAll();
    }

    public Optional<Follower> findById(Integer id) {
        return followerRepository.findById(id);
    }

    public Follower save(Follower follower) {
        return followerRepository.save(follower);
    }

    public void deleteById(Integer id) {
        followerRepository.deleteById(id);
    }
 
    @Autowired
    private PhotographerRepository photographerRepository;

    @Transactional
    public boolean toggleFollow(int followerId, int followingId) {
        // Check if the follow relationship already exists
        if (followerRepository.existsByFollowerIdAndFollowing(followerId, followingId)) {
            // If it exists, unfollow (delete the relationship)
            followerRepository.deleteByFollowerIdAndFollowing(followerId, followingId);
            return false; // Return false indicating "unfollowed"
        } else {
            // If not, create a follow relationship
            Photographer follower = photographerRepository.findById(followerId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid follower ID"));
            Photographer following = photographerRepository.findById(followingId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid following ID"));

            Follower newFollower = new Follower();
            newFollower.setFollowerId(followerId);
            newFollower.setFollowing(followingId);
            followerRepository.save(newFollower);
            return true; // Return true indicating "followed"
        }
    }

    public boolean isFollowing(int followerId, int followingId) {
        return followerRepository.existsByFollowerIdAndFollowing(followerId, followingId);
    }
}
