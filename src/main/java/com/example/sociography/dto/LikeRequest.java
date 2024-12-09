package com.example.sociography.dto;

public class LikeRequest {
    private int pictureId;
    private int photographerId;

    // Getters and setters
    public int getPictureId() {
        return pictureId;
    }

    public LikeRequest(int pictureId, int photographerId) {
		this.pictureId = pictureId;
		this.photographerId = photographerId;
	}

	public void setPictureId(int pictureId) {
        this.pictureId = pictureId;
    }

    public int getPhotographerId() {
        return photographerId;
    }

    public void setPhotographerId(int photographerId) {
        this.photographerId = photographerId;
    }
}
