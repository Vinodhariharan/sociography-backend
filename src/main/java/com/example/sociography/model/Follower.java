package com.example.sociography.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "followers")
public class Follower {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "connection_id")
    private int id;

    // Keep following as an int, representing photographer ID
    @Column(name = "following_id", nullable = false)
    private int following;

    // Keep followerId as int representing photographer ID
    @Column(name = "follower_id", nullable = false)
    private int followerId;

    @Column(name = "follower_type", nullable = false)
    private String followerType;

    @Column(name = "conn_timestamp", nullable = false)
    private LocalDateTime timestamp;

    // Getters and setters

    public int getConnectionId() {
        return id;
    }

    public void setConnectionId(int id) {
        this.id = id;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public int getFollowerId() {
        return followerId;
    }

    public void setFollowerId(int followerId) {
        this.followerId = followerId;
    }

    public String getFollowerType() {
        return followerType;
    }

    public void setFollowerType(String followerType) {
        this.followerType = followerType;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
