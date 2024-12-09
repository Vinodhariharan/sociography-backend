package com.example.sociography.dto;

import com.example.sociography.model.Photographer;

public class PhotographerProfileResponse {
    private Photographer photographer;
    private int followersCount;
    private int followingCount;

    public PhotographerProfileResponse(Photographer photographer, int followersCount, int followingCount) {
        this.photographer = photographer;
        this.followersCount = followersCount;
        this.followingCount = followingCount;
    }

    public Photographer getPhotographer() {
        return photographer;
    }

    public void setPhotographer(Photographer photographer) {
        this.photographer = photographer;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(int followersCount) {
        this.followersCount = followersCount;
    }

    public int getFollowingCount() {
        return followingCount;
    }

    public void setFollowingCount(int followingCount) {
        this.followingCount = followingCount;
    }
}
