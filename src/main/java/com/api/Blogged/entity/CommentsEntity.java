package com.api.Blogged.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "comments")
public class CommentsEntity {

	@Id
	@Column(name = "cid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cid;
	@Column(name = "cmt")
	private String comment;
	@Column(name = "blog_id")
	private Integer blogId;
	@Column(name = "username")
	private String username;
	@Column(name = "created", updatable = false, insertable = false)
	private Timestamp createdTs;

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String cmt) {
		this.comment = cmt;
	}

	public Integer getBlogId() {
		return blogId;
	}

	public void setBlogId(Integer blogId) {
		this.blogId = blogId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Timestamp getCreatedTs() {
		return createdTs;
	}

	public void setCreatedTs(Timestamp createdTs) {
		this.createdTs = createdTs;
	}

	@Override
	public String toString() {
		return "CommentsEntity [cid=" + cid + ", comment=" + comment + ", blogId=" + blogId + ", username=" + username
				+ ", createdTs=" + createdTs + "]";
	}

}
