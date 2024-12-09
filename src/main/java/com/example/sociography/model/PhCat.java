package com.example.sociography.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Ph_Cat")
public class PhCat {

    @EmbeddedId
    private PhCatId id;

    @ManyToOne
    @MapsId("phId")
    @JoinColumn(name = "ph_id")
    private Photographer photographer;

    @ManyToOne
    @MapsId("catId")
    @JoinColumn(name = "cat_id")
    private Category category;

	public PhCatId getId() {
		return id;
	}

	public void setId(PhCatId id) {
		this.id = id;
	}

	public Photographer getPhotographer() {
		return photographer;
	}

	public void setPhotographer(Photographer photographer) {
		this.photographer = photographer;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

    // Getters and Setters
}

