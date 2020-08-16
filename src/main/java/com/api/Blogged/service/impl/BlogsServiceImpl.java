package com.api.Blogged.service.impl;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.Blogged.dto.BlogsDto;
import com.api.Blogged.entity.BlogsEntity;
import com.api.Blogged.repo.BlogsRepo;
import com.api.Blogged.service.BlogsService;

@Service
public class BlogsServiceImpl implements BlogsService{

	private static final Logger LOG = LoggerFactory.getLogger(BlogsServiceImpl.class);
	
	@Autowired
	private BlogsRepo blogsRepo;
	
	@Override
	public List<BlogsDto> getAllBlogs() {
		try {
			List<BlogsEntity> blogList = blogsRepo.findAllBlogs();
			List<BlogsDto> blogDtoList = new ArrayList<>();
			for(BlogsEntity be: blogList) {
				BlogsDto dto = new BlogsDto();
				dto.setBlogId(be.getBlogId());
				dto.setHeader(be.getHeader());
				dto.setContent(new String(be.getContent(), StandardCharsets.UTF_8));
				dto.setGenres(be.getGenres());
				dto.setCreatedBy(be.getCreatedBy());
				dto.setCreatedTs(be.getCreatedTs());
				dto.setLastUpdated(be.getLastUpdated());
				blogDtoList.add(dto);
			}
			return blogDtoList;
		} catch (Exception e) {
			LOG.error("Error occurred - {}", e.getMessage());
		}
		return new ArrayList<>();
	}

	@Override
	public void saveBlog(BlogsDto blogsDto) throws Exception{
		BlogsEntity blogsEntity = new BlogsEntity();
		blogsEntity.setHeader(blogsDto.getHeader());
		blogsEntity.setContent(blogsDto.getContent().getBytes());
		blogsEntity.setCreatedBy(blogsDto.getCreatedBy());
		blogsEntity.setGenres(blogsDto.getGenres());
		blogsRepo.saveAndFlush(blogsEntity);
	}

}
