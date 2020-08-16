package com.api.Blogged.service;

import java.util.List;

import com.api.Blogged.dto.BlogsDto;

public interface BlogsService {
	
	public List<BlogsDto> getAllBlogs();
	public void saveBlog(BlogsDto blogsDto) throws Exception;

}
