package com.example.sociography.dto;

import com.example.sociography.model.Picture;
import java.util.List;

public class PhotographerProfileDTO {
    private String name;
    private String selfInfo;
    private byte[] profilePic;
    private int followersCount;
    private int followingCount;
    private List<Picture> pictures;

    public PhotographerProfileDTO(String name, String selfInfo, byte[] profilePic, int followersCount, int followingCount, List<Picture> pictures) {
        this.name = name;
        this.selfInfo = selfInfo;
        this.profilePic = profilePic;
        this.followersCount = followersCount;
        this.followingCount = followingCount;
        this.pictures = pictures;
    }

    public byte[] getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(byte[] profilePic) {
		this.profilePic = profilePic;
	}

	// Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSelfInfo() {
        return selfInfo;
    }

    public void setSelfInfo(String selfInfo) {
        this.selfInfo = selfInfo;
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

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }
}

