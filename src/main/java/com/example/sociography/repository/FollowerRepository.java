package com.example.sociography.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.sociography.model.Follower;

public interface FollowerRepository extends JpaRepository<Follower, Integer> {

//    @Query("SELECT COUNT(f) FROM Follower f WHERE f.following.id = :photographerId AND f.followerType = 'photographer'")
//    int countFollowersByPhotographerId(@Param("photographerId") int photographerId);
//
//    @Query("SELECT COUNT(f) FROM Follower f WHERE f.following.id = :photographerId AND f.followerType = 'photographer'")
//    int countFollowingByPhotographerId(@Param("photographerId") int photographerId);
//    
//    boolean existsByFollowerIdAndFollowingId(int followerId, int followingId);
//    
//    void deleteByFollowerIdAndFollowingId(int followerId, int followingId);
	@Query("SELECT COUNT(f) FROM Follower f WHERE f.following = :photographerId AND f.followerType = 'photographer'")
    int countFollowersByPhotographerId(@Param("photographerId") int photographerId);

    // Count following (based on ID) - corrected for int field
    @Query("SELECT COUNT(f) FROM Follower f WHERE f.followerId = :photographerId AND f.followerType = 'photographer'")
    int countFollowingByPhotographerId(@Param("photographerId") int photographerId);

    // Check if a photographer is already followed by another photographer (followerId and followingId)
    boolean existsByFollowerIdAndFollowing(int followerId, int followingId);

    // Delete a follower-following relationship
    void deleteByFollowerIdAndFollowing(int followerId, int followingId);

}
