package com.example.sociography.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Photographers")
public class Photographer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ph_id")
    private int id;

    @Column(name = "ph_name")
    private String name;

    @Column(name = "ph_username")
    private String username;

    @Column(name = "ph_password")
    private String password;

    @Column(name = "ph_dob")
    private LocalDate dob;

    @Lob
    @Column(name = "ph_profilepic")
    private byte[] profilePic;

    @Column(name = "ph_self_info")
    private String selfInfo;

    @Column(name = "ph_location")
    private String location;

    @Column(name = "ph_contact")
    private String contact;

    @Column(name = "ph_email")
    private String email;

    @OneToMany(mappedBy = "following", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Follower> followers;
    
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

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public byte[] getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(byte[] profilePic) {
		this.profilePic = profilePic;
	}

	public String getSelfInfo() {
		return selfInfo;
	}

	public void setSelfInfo(String selfInfo) {
		this.selfInfo = selfInfo;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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
    
    

    // Getters and Setters
}
