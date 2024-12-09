package com.example.sociography.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PhCatId implements Serializable {

    private int phId;
    private int catId;

    public PhCatId() {}

    public PhCatId(int phId, int catId) {
        this.phId = phId;
        this.catId = catId;
    }

	public int getPhId() {
		return phId;
	}

	public void setPhId(int phId) {
		this.phId = phId;
	}

	public int getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(catId, phId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PhCatId other = (PhCatId) obj;
		return Objects.equals(catId, other.catId) && Objects.equals(phId, other.phId);
	}
    

    // Getters, Setters, equals(), hashCode()
}

