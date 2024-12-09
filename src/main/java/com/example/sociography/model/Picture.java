package com.example.sociography.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "Pictures")
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pic_id")
    private int id;

    @Lob
    @Column(name = "picture")
    private byte[] picture;

    @ManyToOne
    @JoinColumn(name = "ph_id", nullable = false)
    private Photographer photographer;

    @Column(name = "pic_timestamp")
    private LocalDateTime timestamp;

    @Column(name = "location")
    private String location;

    @Column(name = "pic_description")
    private String description;

    @Column(name = "share_link")
    private String shareLink;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getShareLink() {
		return shareLink;
	}

	public void setShareLink(String shareLink) {
		this.shareLink = shareLink;
	}
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Picture picture1 = (Picture) o;
        return id == picture1.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

	
}
