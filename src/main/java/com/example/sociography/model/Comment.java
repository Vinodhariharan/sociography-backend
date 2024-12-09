package com.example.sociography.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comm_id")
    private int id;

    @Column(name = "comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "pic_id", nullable = false)
    private Picture picture;

    @ManyToOne
    @JoinColumn(name = "ph_id", nullable = false)
    private Photographer photographer;

    @Column(name = "comm_timestamp")
    private LocalDateTime timestamp;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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

    // Getters and Setters
}

