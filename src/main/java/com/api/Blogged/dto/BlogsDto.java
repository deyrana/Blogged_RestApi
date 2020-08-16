package com.api.Blogged.dto;

import java.sql.Timestamp;

public class BlogsDto {

	private Integer blogId;
	private String header;
	private String content;
	private String createdBy;
	private String genres;
	private Timestamp createdTs;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
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
		return "BlogsDto [blogId=" + blogId + ", header=" + header + ", content=" + content + ", createdBy=" + createdBy
				+ ", genres=" + genres + ", createdTs=" + createdTs + ", lastUpdated=" + lastUpdated + "]";
	}

}
