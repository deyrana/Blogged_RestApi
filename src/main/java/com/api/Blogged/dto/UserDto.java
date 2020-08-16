package com.api.Blogged.dto;

public class UserDto {

	private String name;
	private String username;
	private String password;
	private String email;
	private String dob;
	private String genres;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getGenres() {
		return genres;
	}

	public void setGenres(String genres) {
		this.genres = genres;
	}

	@Override
	public String toString() {
		return "UserDto [name=" + name + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", dob=" + dob + ", genres=" + genres + "]";
	}

}
