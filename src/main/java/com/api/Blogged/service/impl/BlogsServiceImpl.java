package com.api.Blogged.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.Blogged.dto.BlogsCompleteDto;
import com.api.Blogged.dto.BlogsDto;
import com.api.Blogged.entity.BlogsEntity;
import com.api.Blogged.repo.BlogsRepo;
import com.api.Blogged.service.BlogsService;
import com.api.Blogged.util.FileUtils;

@Service
public class BlogsServiceImpl implements BlogsService {

	private static final Logger LOG = LoggerFactory.getLogger(BlogsServiceImpl.class);

	@Autowired
	private BlogsRepo blogsRepo;

	@Override
	public List<BlogsCompleteDto> getAllBlogs() {
		try {
			List<BlogsCompleteDto> blogsCompleteDtos = blogsRepo.findAllBlogs();
			for(BlogsCompleteDto dto: blogsCompleteDtos) {
				dto.setImage(FileUtils.decompressBytes(dto.getImage()));
			}
			return blogsCompleteDtos;
		} catch (Exception e) {
			LOG.error("Error occurred - {}", e.getMessage());
		}
		return new ArrayList<>();
	}

	@Override
	public void saveBlog(BlogsDto blogsDto) throws Exception {
		BlogsEntity blogsEntity = new BlogsEntity();
		blogsEntity.setHeader(blogsDto.getHeader());
		blogsEntity.setContent(blogsDto.getContent().getBytes());
		blogsEntity.setCreatedBy(blogsDto.getCreatedBy());
		blogsEntity.setGenres(blogsDto.getGenres());
		blogsRepo.saveAndFlush(blogsEntity);
	}

	@Override
	public BlogsCompleteDto getBlog(Integer blogid) {
		BlogsCompleteDto blogsCompleteDto = blogsRepo.findBlogDetail(blogid);
		blogsCompleteDto.setImage(FileUtils.decompressBytes(blogsCompleteDto.getImage()));
		return blogsCompleteDto;
	}

}
