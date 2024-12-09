package com.example.sociography.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Partners")
public class Partner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ptn_id")
    private int id;

    @Column(name = "ptn_name")
    private String name;

    @Column(name = "ptn_contact")
    private String contact;

    @Column(name = "ptn_email")
    private String email;

    @Lob
    @Column(name = "ptn_profilepic")
    private byte[] profilePic;

    @Column(name = "ptn_username")
    private String username;

    @Column(name = "ptn_password")
    private String password;

    @Column(name = "ptn_tagline")
    private String tagline;

    @Column(name = "ptn_description")
    private String description;

    @Column(name = "ptn_address")
    private String address;

    @Column(name = "website")
    private String website;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public byte[] getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(byte[] profilePic) {
		this.profilePic = profilePic;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTagline() {
		return tagline;
	}

	public void setTagline(String tagline) {
		this.tagline = tagline;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

    // Getters and Setters
}
