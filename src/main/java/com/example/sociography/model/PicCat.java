package com.example.sociography.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Pic_Cat")
public class PicCat {

    @EmbeddedId
    private PicCatId id;

    @ManyToOne
    @MapsId("picId")  // Maps the picId field of the composite key to the Picture entity
    @JoinColumn(name = "pic_id")
    private Picture picture;

    @ManyToOne
    @MapsId("catId")  // Maps the catId field of the composite key to the Category entity
    @JoinColumn(name = "cat_id")
    private Category category;

    public PicCat() {}

    // Constructor with parameters
    public PicCat(PicCatId id, Picture picture, Category category) {
        this.id = id;
        this.picture = picture;
        this.category = category;
    }

    // Getters and Setters
    public PicCatId getId() {
        return id;
    }

    public void setId(PicCatId id) {
        this.id = id;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
