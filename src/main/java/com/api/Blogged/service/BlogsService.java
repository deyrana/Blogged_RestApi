package com.api.Blogged.service;

import java.util.List;

import com.api.Blogged.dto.BlogsCompleteDto;
import com.api.Blogged.dto.BlogsDto;

public interface BlogsService {

	public List<BlogsCompleteDto> getAllBlogs();

	public void saveBlog(BlogsDto blogsDto) throws Exception;

	public BlogsCompleteDto getBlog(Integer blogid);

	public String deleteBlog(int blogid);
	
	public void setBlogToFav(String username, int blogId);
	
	public boolean getfavBlog(String username, int blogId);
	
	public void removeFavBlog(String username, int blogId);
	
	public long getFavBlogCount(String username);
}
