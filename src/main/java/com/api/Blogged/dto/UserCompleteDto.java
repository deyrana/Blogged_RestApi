package com.api.Blogged.dto;

import java.util.Arrays;
import java.util.Date;

public class UserCompleteDto {

	private Integer userId;
	private String name;
	private String username;
	private String email;
	private Date dateOfBirth;
	private String genres;
	private byte[] image;
	private long blogCount;

	public UserCompleteDto(Integer userId, String name, String username, String email, Date dateOfBirth, String genres,
			byte[] image, long blogCount) {
		super();
		this.userId = userId;
		this.name = name;
		this.username = username;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.genres = genres;
		this.image = image;
		this.blogCount = blogCount;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGenres() {
		return genres;
	}

	public void setGenres(String genres) {
		this.genres = genres;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public long getBlogCount() {
		return blogCount;
	}

	public void setBlogCount(long blogCount) {
		this.blogCount = blogCount;
	}

	@Override
	public String toString() {
		return "UserCompleteDto [userId=" + userId + ", name=" + name + ", username=" + username + ", email=" + email
				+ ", dateOfBirth=" + dateOfBirth + ", genres=" + genres + ", image=" + Arrays.toString(image)
				+ ", blogCount=" + blogCount + "]";
	}

}
