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
import com.api.Blogged.entity.CommentsEntity;
import com.api.Blogged.entity.FavouriteBlogsEntity;
import com.api.Blogged.repo.BlogsRepo;
import com.api.Blogged.repo.CommentsRepo;
import com.api.Blogged.repo.FavouriteBlogsRepo;
import com.api.Blogged.service.BlogsService;

@Service
public class BlogsServiceImpl implements BlogsService {

	private static final Logger LOG = LoggerFactory.getLogger(BlogsServiceImpl.class);

	@Autowired
	private BlogsRepo blogsRepo;

	@Autowired
	private FavouriteBlogsRepo favouriteBlogsRepo;
	
	@Autowired
	private CommentsRepo commentsRepo;

	@Override
	public List<BlogsCompleteDto> getAllBlogs() {
		try {
			List<BlogsCompleteDto> blogsCompleteDtos = blogsRepo.findAllBlogs();
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
		return blogsCompleteDto;
	}

	@Override
	public String deleteBlog(int blogid) {
		try {
			favouriteBlogsRepo.removeFavBlogByBlogId(blogid);
			commentsRepo.removeCommentsByBlogId(blogid);
			blogsRepo.deleteById(blogid);
			return "Blog no " + blogid + " deleted successfully";
		} catch (Exception e) {
			LOG.error("Error occurred - {}", e.getMessage());
		}
		return "Error occurred while deleting blog";
	}

	@Override
	public void setBlogToFav(String username, int blogId) {
		try {
			FavouriteBlogsEntity favouriteBlogsEntity = new FavouriteBlogsEntity();
			favouriteBlogsEntity.setUsername(username);
			favouriteBlogsEntity.setBlog_id(blogId);
			if (favouriteBlogsRepo.findFavBlog(username, blogId) == null) {
				favouriteBlogsRepo.saveAndFlush(favouriteBlogsEntity);
			} else {
				LOG.info("Already marked as favourite");
			}
		} catch (Exception e) {
			LOG.error("Error occurred while setting blog# {} to favourite user- {} - {}", blogId, username,
					e.getMessage());
		}
	}

	@Override
	public boolean getfavBlog(String username, int blogId) {
		try {
			return favouriteBlogsRepo.findFavBlog(username, blogId) != null;
		} catch (Exception e) {
			LOG.error("Error occurred while fetching fav");
		}
		return false;
	}

	@Override
	public void removeFavBlog(String username, int blogId) {
		try {
			favouriteBlogsRepo.removeFavBlog(username, blogId);
		} catch (Exception e) {
			LOG.error("Error occurred - {}", e.getMessage());
		}
	}

	@Override
	public long getFavBlogCount(String username) {
		try {
			return favouriteBlogsRepo.getFavBlogCount(username);
		} catch (Exception e) {
			LOG.error("Error occurred while fetching Fav Blog Count- {}", e.getMessage());
		}
		return 0;
	}

	@Override
	public List<CommentsEntity> getAllCommentsForBlog(int blogId) {
		try {
			return commentsRepo.getAllCommentsForBlog(blogId);
		} catch (Exception e) {
			LOG.error("Error occurred - {}", e.getMessage());
		}
		return null;
	}

	@Override
	public void saveComment(CommentsEntity commentsEntity) {
		try {
			commentsRepo.saveAndFlush(commentsEntity);
		} catch (Exception e) {
			LOG.error("Error occurred while saving comments - {}", e.getMessage());
		}
	}

	@Override
	public List<BlogsCompleteDto> getFavBlogs(String username) {
		
		try {
			return favouriteBlogsRepo.getFavBlogs(username);
		} catch (Exception e) {
			LOG.error("Error occurred - {}", e.getMessage());
		}
		return new ArrayList<>();
	}

	
}
