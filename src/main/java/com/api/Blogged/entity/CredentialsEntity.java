package com.api.Blogged.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "credentials")
public class CredentialsEntity {

	@Id
	@Column(name = "user_id")
	private Integer user_id;
	@Column(name = "username", nullable = false)
	private String username;
	@Column(name = "password", nullable = false)
	private String password;
	@Column(name = "created", insertable = false, updatable = false)
	private Timestamp createdts;
	@Column(name = "lastupdated", insertable = false, updatable = false)
	private Timestamp lastUpdatedTs;

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
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

	public Timestamp getCreatedts() {
		return createdts;
	}

	public void setCreatedts(Timestamp createdts) {
		this.createdts = createdts;
	}

	public Timestamp getLastUpdatedTs() {
		return lastUpdatedTs;
	}

	public void setLastUpdatedTs(Timestamp lastUpdatedTs) {
		this.lastUpdatedTs = lastUpdatedTs;
	}

	@Override
	public String toString() {
		return "CredentialsEntity [user_id=" + user_id + ", username=" + username + ", password=" + password
				+ ", createdts=" + createdts + ", lastUpdatedTs=" + lastUpdatedTs + "]";
	}

}
