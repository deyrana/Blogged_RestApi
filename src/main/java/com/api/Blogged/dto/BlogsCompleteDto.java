package com.api.Blogged.dto;

import java.nio.charset.StandardCharsets;
import java.util.Date;

public class BlogsCompleteDto {

	private int blogId;
	private String header;
	private byte[] content;
	private String createdBy;
	private String genres;
	private Date createdTs;
	private Date lastUpdated;
	private byte[] image;
	private String contentStr;

	public BlogsCompleteDto(int blogId, String header, byte[] content, String createdBy, String genres, Date createdTs,
			Date lastUpdated, byte[] image) {
		super();
		this.blogId = blogId;
		this.header = header;
		this.content = content;
		this.createdBy = createdBy;
		this.genres = genres;
		this.createdTs = createdTs;
		this.lastUpdated = lastUpdated;
		this.image = image;
		this.contentStr = new String(content, StandardCharsets.UTF_8);
	}
	
	public BlogsCompleteDto(int blogId, String header, byte[] content, String createdBy, String genres, Date createdTs,
			Date lastUpdated) {
		super();
		this.blogId = blogId;
		this.header = header;
		this.content = content;
		this.createdBy = createdBy;
		this.genres = genres;
		this.createdTs = createdTs;
		this.lastUpdated = lastUpdated;
		this.contentStr = new String(content, StandardCharsets.UTF_8);
	}

	public int getBlogId() {
		return blogId;
	}

	public void setBlogId(int blogId) {
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

	public Date getCreatedTs() {
		return createdTs;
	}

	public void setCreatedTs(Date createdTs) {
		this.createdTs = createdTs;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getContentStr() {
		return contentStr;
	}

	public void setContentStr(String contentStr) {
		this.contentStr = contentStr;
	}

	@Override
	public String toString() {
		return "BlogsCompleteDto [blogId=" + blogId + ", header=" + header + ", createdBy=" + createdBy + ", genres="
				+ genres + ", createdTs=" + createdTs + ", lastUpdated=" + lastUpdated + ", contentStr=" + contentStr
				+ "]";
	}

}
