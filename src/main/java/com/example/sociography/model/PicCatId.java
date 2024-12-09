package com.example.sociography.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PicCatId implements Serializable {

    private int picId;
    private int catId;

    public PicCatId() {}

    public PicCatId(int picId, int catId) {
        this.picId = picId;
        this.catId = catId;
    }

	public int getPicId() {
		return picId;
	}

	public void setPicId(int picId) {
		this.picId = picId;
	}

	public int getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(catId, picId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PicCatId other = (PicCatId) obj;
		return catId == other.catId && picId == other.picId;
	}

    // Getters, Setters, equals(), hashCode()
}
