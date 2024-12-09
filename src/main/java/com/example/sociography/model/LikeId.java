package com.example.sociography.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class LikeId implements Serializable {

    private int picId;
    private int phId;

    // Constructors, getters, setters, equals, and hashCode methods

    public LikeId() {}

    public LikeId(int picId, int phId) {
        this.picId = picId;
        this.phId = phId;
    }

    public int getPicId() {
        return picId;
    }

    public void setPicId(int picId) {
        this.picId = picId;
    }

    public int getPhId() {
        return phId;
    }

    public void setPhId(int phId) {
        this.phId = phId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LikeId likeId = (LikeId) o;
        return picId == likeId.picId && phId == likeId.phId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(picId, phId);
    }
}
