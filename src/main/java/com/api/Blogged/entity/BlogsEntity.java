package com.api.Blogged.entity;

import java.sql.Timestamp;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "blogs")
public class BlogsEntity {

	@Id
	@Column(name = "blog_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer blogId;
	@Column(name = "heading", nullable = false)
	private String header;
	@Column(name = "content", nullable = false)
	private byte[] content;
	@Column(name = "created_by", nullable = false)
	private String createdBy;
	@Column(name = "genres", nullable = true)
	private String genres;
	@Column(name = "created", updatable = false, insertable = false)
	private Timestamp createdTs;
	@Column(name = "last_updated", updatable = false, insertable = false)
	private Timestamp lastUpdated;

	public Integer getBlogId() {
		return blogId;
	}

	public void setBlogId(Integer blogId) {
		this.blogId = blogId;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getGenres() {
		return genres;
	}

	public void setGenres(String genres) {
		this.genres = genres;
	}

	public Timestamp getCreatedTs() {
		return createdTs;
	}

	public void setCreatedTs(Timestamp createdTs) {
		this.createdTs = createdTs;
	}

	public Timestamp getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Timestamp lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	@Override
	public String toString() {
		return "BlogsEntity [blogId=" + blogId + ", header=" + header + ", content=" + Arrays.toString(content)
				+ ", createdBy=" + createdBy + ", genres=" + genres + ", createdTs=" + createdTs + ", lastUpdated="
				+ lastUpdated + "]";
	}

}
