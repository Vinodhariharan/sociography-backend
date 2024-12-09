package com.example.sociography.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "likes")
public class Like {

    @EmbeddedId
    private LikeId id;

    @ManyToOne
    @MapsId("picId") // This is the name of the attribute in LikeId
    @JoinColumn(name = "pic_id", nullable = false)
    private Picture picture;

    @ManyToOne
    @MapsId("phId") // This is the name of the attribute in LikeId
    @JoinColumn(name = "ph_id", nullable = false)
    private Photographer photographer;

    @Column(name = "like_timestamp")
    private LocalDateTime timestamp;

    public LikeId getId() {
        return id;
    }

    public void setId(LikeId id) {
        this.id = id;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public Photographer getPhotographer() {
        return photographer;
    }

    public void setPhotographer(Photographer photographer) {
        this.photographer = photographer;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
